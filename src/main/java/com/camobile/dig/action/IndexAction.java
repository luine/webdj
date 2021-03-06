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

package com.camobile.dig.action;

import java.util.List;

import org.seasar.cubby.action.ActionClass;
import org.seasar.cubby.action.ActionResult;
import org.seasar.cubby.action.Forward;
import org.seasar.cubby.action.Json;
import org.seasar.cubby.action.Path;
import org.seasar.cubby.action.RequestParameter;

import com.camobile.dig.model.Response;
import com.camobile.dig.service.SoxInterfaceService;
import com.camobile.dig.service.XmlBindService;

@ActionClass
@Path("/")
public class IndexAction {

	@RequestParameter
	public static String xml;
	@RequestParameter
	public static String mode;
	@RequestParameter
	public static String filename;
	
	public ActionResult index() {

		Response res = new Response();
		
		try {
			List nodes = XmlBindService.parse(xml);
			SoxInterfaceService.make(nodes, filename);
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setResult("NG");
			return new Json(res);
		}

		res.setResult("OK");
		
		return new Json(res);
	}
}
