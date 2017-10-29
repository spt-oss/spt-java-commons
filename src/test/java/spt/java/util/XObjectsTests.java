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
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tests for {@link XObjects}
 */
public class XObjectsTests {
	
	/**
	 * A
	 */
	private static final String A = "a";
	
	/**
	 * B
	 */
	private static final String B = "b";
	
	/**
	 * {@link XObjects#firstNonNull(Object...)}
	 */
	@Test
	public void firstNonNull() {
		
		assertThat(XObjects.firstNonNull(1, 2)).isEqualTo(1);
		assertThat(XObjects.firstNonNull(null, 1, 2)).isEqualTo(1);
		assertThat(XObjects.firstNonNull(null, null, 2)).isEqualTo(2);
		assertThat(XObjects.firstNonNull(null, null, 2, null)).isEqualTo(2);
		
		assertThat(XObjects.firstNonNull(A, B)).isEqualTo(A);
		assertThat(XObjects.firstNonNull(null, A, B)).isEqualTo(A);
		assertThat(XObjects.firstNonNull(null, null, B)).isEqualTo(B);
		assertThat(XObjects.firstNonNull(null, null, B, null)).isEqualTo(B);
		
		assertThat(XObjects.firstNonNull(null, A, 1)).isEqualTo(A);
		
		try {
			
			XObjects.firstNonNull((Integer) null);
			
			fail();
		}
		catch (IllegalArgumentException e) {
			
			/* NOP */
		}
		
		try {
			
			XObjects.firstNonNull((Integer[]) null);
			
			fail();
		}
		catch (NullPointerException e) {
			
			/* NOP */
		}
	}
}
