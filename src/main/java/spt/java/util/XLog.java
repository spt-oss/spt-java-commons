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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.helpers.MessageFormatter;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Log message builder for logger and {@link Exception}
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class XLog {
	
	/**
	 * Message parameter separator
	 */
	protected static final String MESSAGE_PARAMETER_SEPARATOR = ": ";
	
	/**
	 * Parameters separator
	 */
	protected static final String PARAMETERS_SEPARATOR = ", ";
	
	/**
	 * Entry key-value separator
	 */
	protected static final String ENTRY_KEY_VALUE_SEPARATOR = "=";
	
	/**
	 * Message
	 */
	@NonNull
	private final String message;
	
	/**
	 * Binding variables
	 */
	private List<Object> binds = new ArrayList<>();
	
	/**
	 * {@link Parameter}s
	 */
	private List<Parameter> parameters = new ArrayList<>();
	
	/**
	 * Create {@link XLog}
	 * 
	 * @param message message
	 * @return {@link XLog}
	 */
	public static XLog of(String message) {
		
		return new XLog(message);
	}
	
	/**
	 * Create {@link XLog}
	 * 
	 * @param message message
	 * @return {@link XLog}
	 */
	public static XLog of(@NonNull StringBuilder message) {
		
		return new XLog(message.toString());
	}
	
	/**
	 * Add binding variables and build message
	 * 
	 * @param binds binding variables
	 * @return final message
	 */
	public String bind(@NonNull Object... binds) {
		
		return this.bindAnd(binds).toString();
	}
	
	/**
	 * Add binding variables
	 * 
	 * @param binds binding variables
	 * @return {@link XLog}
	 */
	public XLog bindAnd(@NonNull Object... binds) {
		
		this.binds.addAll(Arrays.asList(binds));
		
		return this;
	}
	
	/**
	 * Add parameter values and build message
	 * 
	 * @param values parameter values
	 * @return final message
	 */
	public String value(@NonNull Object... values) {
		
		return this.valueAnd(values).toString();
	}
	
	/**
	 * Add parameter values
	 * 
	 * @param values parameter values
	 * @return {@link XLog}
	 */
	public XLog valueAnd(@NonNull Object... values) {
		
		for (Object value : values) {
			
			this.parameters.add(new ValueParameter(value));
		}
		
		return this;
	}
	
	/**
	 * Add parameter entry and build message
	 * 
	 * @param key entry key
	 * @param value entry value
	 * @return final message
	 */
	public String entry(String key, Object value) {
		
		return this.entryAnd(key, value).toString();
	}
	
	/**
	 * Add parameter entry
	 * 
	 * @param key entry key
	 * @param value entry value
	 * @return {@link XLog}
	 */
	public XLog entryAnd(String key, Object value) {
		
		this.parameters.add(new EntryParameter(key, value));
		
		return this;
	}
	
	/**
	 * Build message
	 * 
	 * @return final message
	 */
	@Override
	public String toString() {
		
		StringBuilder buffer = new StringBuilder();
		
		// Binding variables
		if (!this.binds.isEmpty()) {
			
			buffer.append(MessageFormatter.arrayFormat(this.message, this.binds.toArray()).getMessage());
		}
		else {
			
			buffer.append(this.message);
		}
		
		// Parameters
		if (!this.parameters.isEmpty()) {
			
			buffer.append(MESSAGE_PARAMETER_SEPARATOR);
			
			for (Parameter parameter : this.parameters) {
				
				buffer.append(parameter.toString());
				buffer.append(PARAMETERS_SEPARATOR);
			}
			
			buffer.setLength(buffer.length() - PARAMETERS_SEPARATOR.length());
		}
		
		return buffer.toString();
	}
	
	/**
	 * Parameter
	 */
	protected interface Parameter {
		
		/* NOP */
	}
	
	/**
	 * {@link Parameter} for value
	 */
	@RequiredArgsConstructor
	protected static class ValueParameter implements Parameter {
		
		/**
		 * Value
		 */
		private final Object value;
		
		@Override
		public String toString() {
			
			return String.valueOf(this.value);
		}
	}
	
	/**
	 * {@link Parameter} for entry
	 */
	@RequiredArgsConstructor
	protected static class EntryParameter implements Parameter {
		
		/**
		 * Key
		 */
		private final String key;
		
		/**
		 * Value
		 */
		private final Object value;
		
		@Override
		public String toString() {
			
			StringBuilder buffer = new StringBuilder();
			buffer.append(this.key);
			buffer.append(ENTRY_KEY_VALUE_SEPARATOR);
			buffer.append(String.valueOf(this.value));
			
			return buffer.toString();
		}
	}
}
