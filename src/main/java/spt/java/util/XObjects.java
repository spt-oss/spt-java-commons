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

import java.util.Objects;

import lombok.NonNull;

/**
 * Custom {@link Objects}
 */
public class XObjects {
	
	/**
	 * Constructor
	 */
	protected XObjects() {
		
		/* NOP */
	}
	
	/**
	 * Get first non-null object
	 * 
	 * @param objects objects
	 * @param <T> object type
	 * @return non-null object
	 * @throws IllegalArgumentException if non-null object not found
	 */
	@SafeVarargs
	public static <T> T firstNonNull(@NonNull T... objects) throws IllegalArgumentException {
		
		for (T object : objects) {
			
			if (object != null) {
				
				return object;
			}
		}
		
		throw new IllegalArgumentException("Non-null object not found");
	}
}
