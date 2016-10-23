package com.gd.steps.serializer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.gd.rest.DefaultResponseHandler;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class StatementParser {
	
	private String path;
	private List<Statement> statements;
	private String category;
	private JsonObject doc;
	public static String project = "1";
	private List<String> invalidStatements = new ArrayList<String>();

	public StatementParser(String path) {
		this.path = path;
		statements = new ArrayList<Statement>();
	}
	
	
	public List<Statement> getStatements() {
		return statements;
	}

	public void processSteps()
	{
   	 	BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		category = new File(path).getName().replace(".rb", "");
		
        String line;
        String doc = null;
        Statement statement = null;
        try {
			while ((line = br.readLine()) != null)
			{
				if(line.startsWith("#Start_DOC"))
				{
					doc = processCucumbeDoc(br, line);
				}
				
				if(line.startsWith("#"))
					continue;
				
				line = line.trim();
				if(line.startsWith("Given(") || line.startsWith("When(") || line.startsWith("Then(") || line.startsWith("And("))
				{
					statement = processStatement(line);
					statement.setDescription(doc);
					statements.add(statement);
				}
			 
			}				
			
			br.close();			
			
			File invalidFile = new File(path.replace(".rb", "-ErrorFormatted.rb"));
			StringBuilder in_Valid = new StringBuilder();
			for(String text : this.invalidStatements)
			{
				in_Valid.append(text);
			}
		    try {
			 
		    	FileOutputStream out = new FileOutputStream(invalidFile);  //得到文件输出流
		    	out.write(in_Valid.toString().getBytes()); //写出文件    
		    } catch (Exception ex) {
		    	ex.printStackTrace(); //输出出错信息
		    }
        }
        catch(IOException e)
        {
        	e.printStackTrace();
        }
		
	}
	
	private String processCucumbeDoc(BufferedReader br, String line) throws IOException {
		// TODO Auto-generated method stub
		doc = new JsonObject();
		String key = null ;
		String value = null ;
		ArrayList<String> desc = null; 
		int count = 0;
		int followcount = 0;
		boolean Stop = false;
		while (!Stop && (line = br.readLine()) != null )
		{
			line = line.trim();
			if(line.equals(""))
				continue;
			else if(line.startsWith("#End_DOC")||line.startsWith("Given(") || line.startsWith("When(") || line.startsWith("Then(") || line.startsWith("And("))
			{
				doc.addProperty(key, desc.toString());
				Stop = true;;
			}
			else if(line.startsWith("#params::"))
			{
				doc.addProperty(key, desc.toString());
				line = proccessParams(br, value);
				count = 0;
				followcount = 0;
			}	
					
			if(count > followcount && line.indexOf("::") > 0) 
			{				
				doc.addProperty(key, desc.toString());				 
				followcount++;
			}	
			
			if(Stop) break;
			
			if(line.indexOf("::") > 0)
			{
				count++;
				desc = new ArrayList<String>();
				key = line.substring(line.indexOf("#") + 1,line.indexOf("::")).trim();
				value = line.split("::")[1].trim();						
				desc.add("\"" + value + "\"");			
			}
			else
			{
				value = line.trim().substring(1);
				desc.add("\"" + value + "\""); 
			}	
			
		}
		System.out.println(doc.toString());
		return doc.toString();
	}


	private String proccessParams(BufferedReader br, String line) throws IOException {
		// TODO Auto-generated method stub
		JsonObject params = new JsonObject();
		String key = null ;
		String value = null ;
		ArrayList<String> desc = null; 
		int count = 0;
		int followcount = 0;
		boolean Stop = false;
		while ((!Stop && (line = br.readLine()) != null) )
		{
			System.out.println(line);
			if(line.trim().equals(""))
				continue;
			else if(line.indexOf("Returns::") > 0 || line.indexOf("Example::") > 0 || line.indexOf("#End_DOC::") > 0||line.startsWith("Given(") || line.startsWith("When(") || line.startsWith("Then(") || line.startsWith("And("))
				Stop = true;
	
			if(count > followcount && line.indexOf("::") > 0) 
			{				
				params.addProperty(key, desc.toString());				 
				followcount++;
			}
			
			if(Stop) break;
			
			if(line.indexOf("::") > 0)
			{
				count++;
				desc = new ArrayList<String>();
				key = line.substring(line.indexOf("#") + 1,line.indexOf("::")).trim();
				value = line.split("::")[1].trim();						
				desc.add("\"" + value + "\"");			
			}
			else
			{
				value = line.trim().substring(1);
				desc.add("\"" + value + "\""); 
			}				

		}
		doc.add("params", params);
		return line;
	}


	private Statement processStatement(String line) {
		Statement oStatement; 
		int start = line.indexOf("/");
		int end = line.lastIndexOf("/");
		String statement = line.substring(start+1, end).trim();
		if(statement.startsWith("^"))
		{
			statement = statement.substring(1);
		}
		else
		{
			collectInvalidStatements(line ,path);
		}
		
		if(statement.endsWith("$"))
		{
			statement = statement.substring(0, statement.length()-1);
		}
		else
		{
			collectInvalidStatements(line ,path);
		}
		
		oStatement = new Statement(statement, category);
		oStatement.setContent(line);
		int doIndex = line.indexOf("do" , line.lastIndexOf("/")); 
		start = line.indexOf("|", doIndex); 
		end = line.lastIndexOf("|");
		String[] params = null;
		
		if(start > -1)
		{
			//System.out.println(line);
			params = line.substring(start+1, end).split(",");
			int argindex = 0;
			for(String param : params)
			{
				if(!"".equals(param))
				{	
					param = param.trim();
					if(param.equalsIgnoreCase("pageName"))
					{
						oStatement.add(new Argument(argindex, param, "Page"));
					}
					else if(param.equalsIgnoreCase("elementName"))
					{
						oStatement.add(new Argument(argindex, param, "Element"));
					}
					else
					{
						oStatement.add(new Argument(argindex, param));
					}						
					
					argindex++;
				}
				
			}
		}

		return oStatement;		
		
	}



	private void collectInvalidStatements(String line, String path) {
		
		
		this.invalidStatements.add(line);
		
	}

	public void convertCategory()
	{
		CategorySerializer oCategorySerializer = serializeCategory(category, project);
		try {
			System.out.println(new Gson().toJson(oCategorySerializer));
			String response = whenPostStringRequestUsingHttpClient(new Gson().toJson(oCategorySerializer), "http://127.0.0.1:8000/sm-cw/savecategory/");
			System.out.println(response);
			JsonParser parser = new JsonParser();
			JsonObject responseBody = parser.parse(response).getAsJsonObject();
			String category_id = responseBody.get("ID").toString();
			convertStatement(category_id);
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void convertStatement(String categoryid)
	{
		
		System.out.println("convert statements");
		for(Statement s : statements)
		{
			StatementSerializer oStatementSerializer = serializeStatement(s, categoryid, project);
			try {
				System.out.println(new Gson().toJson(oStatementSerializer));
				whenPostStringRequestUsingHttpClient(new Gson().toJson(oStatementSerializer), "http://127.0.0.1:8000/sm-cw/savestatement/");
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}
	

	
	public String whenPostStringRequestUsingHttpClient(String json, String endPoint) 
			  throws ClientProtocolException, IOException {
			    CloseableHttpClient client = HttpClients.createDefault();
			    HttpPost httpPost = new HttpPost(endPoint);
			 			    
			    ResponseHandler<String> responseHandler = new DefaultResponseHandler();
			    List<NameValuePair> params = new ArrayList<NameValuePair>(2);
				params.add(new BasicNameValuePair("data", json));
				httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
				String response = client.execute(httpPost, responseHandler);
			    System.out.println(response);
			    //System.out.println(response.getStatusLine().getStatusCode());
			    client.close();
			    return response;
			}
	
	
	private StatementSerializer serializeStatement(Statement statement, String category, String project)
	{
		StatementSerializer sSerializer = new StatementSerializer();
		
		sSerializer.category = category;
		sSerializer.project = project;	
		sSerializer.statement = statement.getStatement();
		sSerializer.content = statement.getContent();
		sSerializer.description = statement.getDescription();
		statement.getArgs();
		ArgumentSerializer aSerializer;
		for(Argument arg : statement.getArgs())
		{
			aSerializer = new ArgumentSerializer();
			aSerializer.argindex = String.valueOf(arg.getArgindex());
			aSerializer.argtype = arg.getArgtype();
			aSerializer.description = arg.getDesc();	
			sSerializer.args.add(aSerializer);
		}
		
		return sSerializer;	
		
	}
	
	private CategorySerializer serializeCategory(String category, String project)
	{
		CategorySerializer oCategorySerializer = new CategorySerializer();
		
		oCategorySerializer.name = category;
		oCategorySerializer.project = project;
		
		return oCategorySerializer;		
	
	}

	
	public ArrayList<String> getStatementArray()
	{
		ArrayList<String> statementArray = new ArrayList<String>();
		for(Statement statement : statements)
		{
			statementArray.add(statement.getStatement());
		}
		
		return statementArray;
	}
}
