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

package spt.java.lang;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import lombok.NonNull;

/**
 * Custom {@link Throwable}
 */
public class XThrowable {
	
	/**
	 * Constructor
	 */
	protected XThrowable() {
		
		/* NOP */
	}
	
	/**
	 * Get messages
	 * 
	 * @param cause root {@link Throwable}
	 * @return messages
	 */
	public static String getMessages(@NonNull Throwable cause) {
		
		List<String> messages = new ArrayList<>();
		
		Throwable child = cause;
		
		while (child != null) {
			
			String message = child.getMessage();
			
			if (StringUtils.hasText(message)) {
				
				messages.add(message);
			}
			
			child = child.getCause();
		}
		
		return String.join(": ", messages);
	}
}
