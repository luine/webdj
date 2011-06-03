/*
 * Copyright 2004-2010 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package com.camobile.dig.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.camobile.dig.model.SamplerItem;
import com.camobile.dig.model.SoundItem;

public class XmlBindService {

	public String getMessage() {
		return "Hello!";
	}

	public static List parse(String xml) throws Exception {
		List l = new ArrayList();
		
        // DOMパーサ用ファクトリの生成
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        // DOM Documentインスタンス用ファクトリの生成
        DocumentBuilder builder = factory.newDocumentBuilder();
        
        InputStream is = new ByteArrayInputStream(xml.getBytes()); 
        // 解析とDocumentインスタンスの取得
        Document doc = builder.parse(is);
		
        XPathFactory xfactory = XPathFactory.newInstance();
        XPath xpath = xfactory.newXPath();
        XPathExpression expr = xpath.compile("//sequence/sound");
        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        
        NodeList nodes = (NodeList) result;
        for (int i = 0; i < nodes.getLength(); i++) {
            Element elem = (Element) nodes.item(i);
            SoundItem sound = new SoundItem();
            sound.setId(elem.getElementsByTagName("id").item(0).getFirstChild().getNodeValue());
            sound.setStart_time(elem.getElementsByTagName("start_time").item(0).getFirstChild().getNodeValue());
            sound.setEnd_time(elem.getElementsByTagName("end_time").item(0).getFirstChild().getNodeValue());
            sound.setFade_in_until(elem.getElementsByTagName("fade_in_until").item(0).getFirstChild().getNodeValue());
            sound.setFade_out_from(elem.getElementsByTagName("fade_out_from").item(0).getFirstChild().getNodeValue());
            l.add(sound);
            System.out.println(sound.toString());
        }
        
        expr = xpath.compile("//sequence/sampler");
        result = expr.evaluate(doc, XPathConstants.NODESET);
        
        nodes = (NodeList) result;
        for (int i = 0; i < nodes.getLength(); i++) {
            Element elem = (Element) nodes.item(i);
            SamplerItem sampler = new SamplerItem();
            sampler.setId(elem.getElementsByTagName("id").item(0).getFirstChild().getNodeValue());
            sampler.setStart_time(elem.getElementsByTagName("start_time").item(0).getFirstChild().getNodeValue());
            sampler.setGain(elem.getElementsByTagName("gain").item(0).getFirstChild().getNodeValue());
            l.add(sampler);
            System.out.println(sampler.toString());
        }
		
		return l;
	}
	
	public static void main(String[] args) {
		
		String xml = "<sequence>"+
		"  <mode>previewだったら低音質、normalなら普通音質、highなら高音質とかそんなアレ</mode>"+
		"  <sound>"+
		"    <id>sound1</id>"+
		"    <start_time>0</start_time>"+
		"    <end_time>50000</end_time>"+
		"    <fade_in_until>0</fade_in_until>"+
		"    <fade_out_from>45000</fade_out_from>"+
		"</sound>" +
		"  <sound>"+
		"    <id>sound2</id>"+
		"    <start_time>0</start_time>"+
		"    <end_time>30000</end_time>"+
		"    <fade_in_until>0</fade_in_until>"+
		"    <fade_out_from>45000</fade_out_from>"+
		"</sound>" +
		"    <sampler>"+
		"    <id>sam_0</id>"+
		"　　　　　　<start_time>12000</start_time>"+
		"　　　　　　<gain>80</gain>"+
		"　　　　</sampler>"+
		"    <sampler>"+
		"    <id>sam_1</id>"+
		"　　　　　　<start_time>10000</start_time>"+
		"　　　　　　<gain>10</gain>"+
		"　　　　</sampler>"+
		"</sequence>";

		try {
			parse(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}