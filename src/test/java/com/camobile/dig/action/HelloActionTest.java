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

import org.seasar.cubby.action.ActionErrors;
import org.seasar.cubby.action.ActionResult;
import org.seasar.cubby.action.Forward;
import org.seasar.cubby.plugins.s2.unit.CubbyTestCase;

public class HelloActionTest extends CubbyTestCase {

	public HelloAction action;

	public ActionErrors actionErrors;

	protected void setUp() throws Exception {
		include("app.dicon");
	}

	public void testIndex() throws Exception {
		ActionResult result = processAction("/hello/");
		assertPathEquals(Forward.class, "index.jsp", result);
	}

	public void setUpMessage() {
		getRequest().addParameter("name", "name1");
	}

	public void testMessage() throws Exception {
		ActionResult result = processAction("/hello/message");
		assertPathEquals(Forward.class, "hello.jsp", result);
		assertEquals("name1", action.name);
		assertEquals("name1 Hello!", action.message);
	}
	
	public void testMessage_validationError() throws Exception {
		ActionResult result = processAction("/hello/message");
		assertPathEquals(Forward.class, "index.jsp", result);
		assertNull(action.name);
		assertEquals(0, actionErrors.getOthers().size());
		assertEquals(1, actionErrors.getFields().size());
		assertEquals(1, actionErrors.getFields().get("name").size());
		assertEquals("あなたの名前は必須です。", actionErrors.getFields().get("name").get(0));
		assertNull(action.name);
		assertNull(action.message);
	}

}
