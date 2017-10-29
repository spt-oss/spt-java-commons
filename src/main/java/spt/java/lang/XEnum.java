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

import lombok.NonNull;

/**
 * Custom {@link Enum}
 */
public class XEnum {
	
	/**
	 * Constructor
	 */
	protected XEnum() {
		
		/* NOP */
	}
	
	/**
	 * Of
	 * 
	 * @param clazz {@link Enum}
	 * @param id ID
	 * @param <E> {@link Enum} type
	 * @param <I> ID type
	 * @return {@link Enum}
	 * @throws IllegalArgumentException if constant not found
	 */
	public static <E extends Enum<E> & XIdentifiable<I>, I> E of(@NonNull Class<E> clazz, @NonNull I id)
		throws IllegalArgumentException {
		
		for (E constant : clazz.getEnumConstants()) {
			
			if (id.equals(constant.getId())) {
				
				return constant;
			}
		}
		
		throw new IllegalArgumentException("Enum constant not found: " + id);
	}
}
