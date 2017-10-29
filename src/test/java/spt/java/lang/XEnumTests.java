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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Tests for {@link XEnum}
 */
public class XEnumTests {
	
	/**
	 * {@link XEnum#of(Class, Object)}
	 */
	@Test
	public void of() {
		
		for (XExampleEnum value : XExampleEnum.values()) {
			
			assertThat(XEnum.of(XExampleEnum.class, value.getId())).isEqualTo(value);
		}
		
		try {
			
			XEnum.of(XExampleEnum.class, 0L);
			
			fail();
		}
		catch (IllegalArgumentException e) {
			
			/* NOP */
		}
	}
	
	/**
	 * Example {@link Enum}
	 */
	@AllArgsConstructor
	protected enum XExampleEnum implements XIdentifiable<Long> {
		
		/**
		 * A
		 */
		A(1L),
		
		/**
		 * B
		 */
		B(2L);
		
		/**
		 * ID
		 */
		@Getter
		private Long id;
	}
}
