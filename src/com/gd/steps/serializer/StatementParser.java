package com.gd.steps.serializer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.gd.common.Configuration;
import com.gd.common.ConverterSettings;
import com.gd.rest.DefaultResponseHandler;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
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



	public String getSaveCategoryEndpoint() {
		String url = null;
		try {
			url = new URIBuilder(ConverterSettings.EndPoint).setPath("/sm-cw/savecategory/").toString();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return url;
	}

	public String getSaveStatementEndpoint() {
		String url = null;
		try {
			url = new URIBuilder(ConverterSettings.EndPoint).setPath("/sm-cw/savestatement/").toString();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return url;
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
				if(isValidStatementLine(line))
				{
					statement = processStatement(line);
					statement.setDescription(doc);
					statements.add(statement);
				}
			 
			}				
			
			br.close();			
			
			
			StringBuilder in_Valid = new StringBuilder();
			for(String text : this.invalidStatements)
			{
				in_Valid.append(text).append("\n");
			}
			
			if(!in_Valid.toString().trim().equals(""))
			{
			    try {
			    	File invalidFile = new File(Configuration.CucumberWorkspace ,Paths.get("framework", Paths.get(path).getFileName().toString().replace(".rb", "-ErrorFormatred.rb")).toString());
			    	FileOutputStream out = new FileOutputStream(invalidFile);  //得到文件输出流
			    	out.write(in_Valid.toString().getBytes()); //写出文件    
			    } catch (Exception ex) {
			    	ex.printStackTrace(); //输出出错信息
			    }
			}

        }
        catch(IOException e)
        {
        	e.printStackTrace();
        }
		
	}
	
	private boolean isValidStatementLine(String line)
	{
		boolean toReturn = false;
		String templine = line.replaceAll("\\s+", "");
		toReturn = templine.startsWith("Given(") || templine.startsWith("When(") || templine.startsWith("Then(") || templine.startsWith("And(");
		
		return toReturn;
	}
	
	private String processCucumbeDoc(BufferedReader br, String line) throws IOException {
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
			else if(line.startsWith("#End_DOC")||isValidStatementLine(line))
			{				
				doc.add(key, toJsonValue(desc));
				Stop = true;;
			}
			else if(line.startsWith("#params::"))
			{
				
				doc.add(key, toJsonValue(desc));
				line = proccessParams(br, value);
				count = 0;
				followcount = 0;
			}	
					
			if(count > followcount && line.indexOf("::") > 0) 
			{				
				//String jsonvalue = stringifyJsonValue(desc); //desc.size() > 1 ? desc.get(0).toString() : desc.toString();
				JsonElement _value = toJsonValue(desc);
				if(_value != null)
					doc.add(key, toJsonValue(desc));				 
				followcount++;
			}	
			
			if(Stop) break;
			
			if(line.indexOf("::") > 0)
			{
				count++;
				desc = new ArrayList<String>();
				key = line.substring(line.indexOf("#") + 1,line.indexOf("::")).trim().toLowerCase();
				value = line.split("::").length >=2 ? line.split("::")[1].trim() : "";						
				desc.add("" + value + "");			
			}
			else
			{
				value = line.trim().substring(1);
				desc.add("" + value + ""); 
			}	
			
		}
		System.out.println(doc.toString());
		return doc.toString();
	}


	private JsonElement toJsonValue(ArrayList<String> desc) {
		JsonElement toReturn = null;
		if(desc.size() > 1)
		{
			toReturn = new Gson().toJsonTree(desc);
		}
		else if(desc.size()==1 && !"".equals(desc.get(0)))
		{

			toReturn = new Gson().toJsonTree(desc.get(0));			
		}

		return toReturn;
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
			else if(line.indexOf("Returns::") > 0 || line.indexOf("Example::") > 0 || line.indexOf("#End_DOC::") > 0||isValidStatementLine(line))
				Stop = true;
	
			if(count > followcount && line.indexOf("::") > 0) 
			{				
				params.add(key, toJsonValue(desc));				 
				followcount++;
			}
			
			if(Stop) break;
			
			if(line.indexOf("::") > 0)
			{
				count++;
				desc = new ArrayList<String>();
				key = line.substring(line.indexOf("#") + 1,line.indexOf("::")).trim().toLowerCase();
				value = line.split("::").length >=2 ? line.split("::")[1].trim() : "";						
				desc.add("" + value + "");			
			}
			else
			{
				value = line.trim().substring(1);
				desc.add("" + value + ""); 
			}				

		}
		if(!params.isJsonNull())
			doc.add("params", params);
		return line;
	}


	private Statement processStatement(String line) {
		Statement oStatement; 
		int start = line.indexOf("/");
		int end = getEndIndex(line);
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
			params = line.substring(start+1, end).split(",");
			Pattern pattern = Pattern.compile("\\(([^(]+)\\)",Pattern.DOTALL);
			Matcher arguments = pattern.matcher(statement);
			
			
			int i = 0;
			while(arguments.find())
			{			
				Matcher isAction = Pattern.compile("[.^$*!?\"+]",Pattern.DOTALL).matcher(arguments.group());
				if(!isAction.find())
				{
					oStatement.add(new Argument(i, params[i], "Action"));
				}
				else
				{
					if(params[i].toLowerCase().contains("pagename"))
					{
						oStatement.add(new Argument(i, params[i], "Page"));
					}
					else if(params[i].toLowerCase().contains("elementname"))
					{
						oStatement.add(new Argument(i, params[i], "Element"));
					}
					else if(params[i].toLowerCase().startsWith("var_"))
					{
						oStatement.add(new Argument(i, params[i], "Variable"));
					}
					else
					{
						oStatement.add(new Argument(i, params[i]));
					}	
				}
				i++;			
			}
			
		}

		return oStatement;		
		
	}
	
	private int getEndIndex(String line)
	{
		
		int index = 0;
		if(line.split("/").length == 3)
		{
			index = line.lastIndexOf("/");
		}
		else if(line.lastIndexOf("/)") > 0)
		{
			index = line.lastIndexOf("/)");
		}
		else
		{
			int doindex = line.lastIndexOf("do");			
			index = line.lastIndexOf("/", doindex);			
		}	
		
		return index;
	}

	private void collectInvalidStatements(String line, String path) {
		
		
		this.invalidStatements.add(line);
		
	}

	public void convertCategory()
	{
		if(statements.isEmpty()) return;
		
		CategorySerializer oCategorySerializer = serializeCategory(category, project);
		try {
			
			System.out.println(new Gson().toJson(oCategorySerializer));
			String response = whenPostStringRequestUsingHttpClient(new Gson().toJson(oCategorySerializer), this.getSaveCategoryEndpoint());
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
				whenPostStringRequestUsingHttpClient(new Gson().toJson(oStatementSerializer), this.getSaveStatementEndpoint());
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
