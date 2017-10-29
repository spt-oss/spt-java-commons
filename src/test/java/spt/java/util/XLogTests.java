/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package spt.java.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * {@link XLog} tests
 */
public class XLogTests {
	
	/**
	 * {@link XLog#of(String)}
	 */
	@Test
	public void ofByString() {
		
		String name = XLogTests.class.getName();
		
		assertThat(XLog.of(name)).isEqualTo(new XLog(name));
	}
	
	/**
	 * {@link XLog#of(StringBuilder)}
	 */
	@Test
	public void ofByStringBuilder() {
		
		String name = XLogTests.class.getName();
		
		assertThat(XLog.of(new StringBuilder(name).append(name).append(name))).isEqualTo(new XLog(name + name + name));
	}
	
	/**
	 * {@link XLog#bind(Object...)}
	 */
	@Test
	public void bind() {
		
		assertThat(XLog.of("bind {}-{} {}").bind(null, 1, "b1")).isEqualTo("bind null-1 b1");
	}
	
	/**
	 * {@link XLog#bindAnd(Object...)}
	 */
	@Test
	public void bindAnd() {
		
		assertThat(XLog.of("bind {} {}").bindAnd(null, 1).value("b2")).isEqualTo("bind null 1: b2");
		assertThat(XLog.of("bind {}").bindAnd(1).entry("b3", 2)).isEqualTo("bind 1: b3=2");
	}
	
	/**
	 * {@link XLog#value(Object...)}
	 */
	@Test
	public void value() {
		
		assertThat(XLog.of("value1").value(1, 2)).isEqualTo("value1: 1, 2");
	}
	
	/**
	 * {@link XLog#valueAnd(Object...)}
	 */
	@Test
	public void valueAnd() {
		
		assertThat(XLog.of("value2").valueAnd(2, "v1").value(2)).isEqualTo("value2: 2, v1, 2");
		assertThat(XLog.of("value3").valueAnd("v2", 1).entry("v3", 3)).isEqualTo("value3: v2, 1, v3=3");
	}
	
	/**
	 * {@link XLog#entry(String, Object)}
	 */
	@Test
	public void entry() {
		
		assertThat(XLog.of("entry1").entry("e1", 1)).isEqualTo("entry1: e1=1");
	}
	
	/**
	 * {@link XLog#entryAnd(String, Object)}
	 */
	@Test
	public void entryAnd() {
		
		assertThat(XLog.of("entry2").entryAnd("e2", 1).entry("e3", "e4")).isEqualTo("entry2: e2=1, e3=e4");
		assertThat(XLog.of("entry3").entryAnd("e5", 1).valueAnd(1).value(2, 3)).isEqualTo("entry3: e5=1, 1, 2, 3");
	}
}
