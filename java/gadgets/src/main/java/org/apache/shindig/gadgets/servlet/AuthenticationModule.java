/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.shindig.gadgets.servlet;

import com.google.common.collect.ImmutableList;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import org.apache.shindig.auth.AnonymousAuthenticationHandler;
import org.apache.shindig.auth.AuthenticationHandler;
import org.apache.shindig.auth.UrlParameterAuthenticationHandler;

import java.util.List;

/**
 * Binds auth types used by gadget rendering. This should be used when running a stand-alone gadget
 * renderer.
 */
public class AuthenticationModule extends AbstractModule {
  @Override
  protected void configure() {
  }

  @Provides
  @Singleton
  List<AuthenticationHandler> provideAuthenticationHandlers(UrlParameterAuthenticationHandler urlParameterAuthHandler,
                                                        AnonymousAuthenticationHandler anonymoustAuthHandler) {
      return ImmutableList.of(urlParameterAuthHandler, anonymoustAuthHandler);
  }
}
