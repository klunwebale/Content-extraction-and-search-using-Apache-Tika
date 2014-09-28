package edu.usc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.tika.sax.ContentHandlerDecorator;
import org.json.JSONObject;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class JSONTableContentHandler extends ContentHandlerDecorator {

	final List <String> skipList = new ArrayList<String>( Arrays.asList("html", "head", "meta", "title", "body") );

	final List uniqueList = new ArrayList<String> ( Arrays.asList("Location", "department", "duration" , "Title", "jobtype", "company"));

	final List  <String>  hash= new ArrayList();
	
	String elementName = "";

	JSONObject json = null;

	String uniqueString = "";

	public JSONTableContentHandler() {
	}

	public void startDocument() throws SAXException {
		json = new JSONObject();
		uniqueString = "";
	}

	public void endDocument() throws SAXException {
	}
	public void characters(char[] ch, int start, int length) throws SAXException {
		if(ch.length > 0 && !skipList.contains(ch)){
			try{
				json.put(elementName, new String(ch));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if( uniqueList.contains("elementName") ){
			uniqueString = uniqueString + new String(ch) ;
		}
	}

	public void startElement(String uri, String localName, String name, Attributes atts) throws SAXException {
		if(!skipList.contains(name)){
			elementName = name;
		}
	}
	public void endElement(String uri, String localName, String name) throws SAXException {
	}
	public String toString() {
		return json.toString();
	}
} 
