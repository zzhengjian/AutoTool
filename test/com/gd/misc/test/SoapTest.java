package com.gd.misc.test;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.junit.Test;
import org.xml.sax.InputSource;

public class SoapTest {
	

	@Test
    public void testFindCustomer() throws Exception {
        // Create SOAP Connection
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();
        
        // Send SOAP Message to SOAP Server
        String url = "http://10.50.14.112/CustomerFinder/CustomerFinderService.svc?wsdl";
        SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), url);

        // print SOAP Response
        System.out.print("Response SOAP Message:");
        soapResponse.writeTo(System.out);
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.parse(new InputSource(new StringReader(soapResponse.getSOAPBody().toString())));
        soapResponse.getSOAPBody().getTextContent();
        
        soapConnection.close();
    }

	
    private SOAPMessage createSOAPRequest() throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("Find");
        soapBodyElem.addChildElement("env").addTextNode("QA4");
        soapBodyElem.addChildElement("product").addTextNode("GDC30|FSC");
        soapBodyElem.addChildElement("customerType").addTextNode("Default|Perso|VIP|ECA");
        soapBodyElem.addChildElement("email").addTextNode("@greendotcorp.com");
        soapBodyElem.addChildElement("acctBalanceAction").addTextNode(">=10");
        soapBodyElem.addChildElement("acctAgeAction").addTextNode(">=10");
        soapBodyElem.addChildElement("appendExtendQuery").addTextNode(" ");
        soapBodyElem.addChildElement("projectKey").addTextNode("1");
        soapBodyElem.addChildElement("isips").addTextNode("false");
        soapBodyElem.addChildElement("isconsolidateprod").addTextNode("false");

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", "urn:CustomerFinderService/Find");

        soapMessage.saveChanges();

        /* Print the request message */
        System.out.print("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
    }
	
	
}
