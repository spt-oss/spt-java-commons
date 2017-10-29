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

import java.lang.Thread.UncaughtExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Simple {@link UncaughtExceptionHandler}
 */
@RequiredArgsConstructor
public class XSimpleUncaughtExceptionHandler implements UncaughtExceptionHandler {
	
	/**
	 * {@link Logger}
	 */
	@NonNull
	private final Logger logger;
	
	/**
	 * Constructor
	 */
	public XSimpleUncaughtExceptionHandler() {
		
		this(LoggerFactory.getLogger(Thread.class));
	}
	
	@Override
	public void uncaughtException(Thread thread, Throwable e) {
		
		if (e != null) {
			
			this.logger.error("Error occurred in thread", e);
		}
	}
}
