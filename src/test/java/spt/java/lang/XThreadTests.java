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

/**
 * Tests for {@link XThread}
 */
public class XThreadTests {
	
	/**
	 * Exception thrown
	 */
	private boolean thrown;
	
	/**
	 * {@link XThread#sleep(long)}
	 */
	@Test
	public void sleep() {
		
		Thread thread = new Thread(new Runnable() {
			
			@SuppressWarnings("synthetic-access")
			@Override
			public void run() {
				
				try {
					
					XThread.sleep(1000);
					
					fail();
				}
				catch (XUncheckedInterruptedException e) {
					
					XThreadTests.this.thrown = true;
				}
			}
		});
		
		thread.start();
		thread.interrupt();
		
		try {
			
			thread.join();
		}
		catch (InterruptedException e) {
			
			throw new IllegalStateException(e);
		}
		
		assertThat(this.thrown).isEqualTo(true);
	}
}
