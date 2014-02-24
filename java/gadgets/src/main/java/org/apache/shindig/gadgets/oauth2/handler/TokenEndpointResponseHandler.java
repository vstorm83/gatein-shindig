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
package org.apache.shindig.gadgets.oauth2.handler;

import org.apache.shindig.gadgets.http.HttpResponse;
import org.apache.shindig.gadgets.oauth2.OAuth2Accessor;

/**
 * When an TokenEndpointResponseHandler is injected into the system it will be
 * called on every response from the token server that it claims to handle.
 *
 * See {@link http://tools.ietf.org/html/draft-ietf-oauth-v2-21#section-4}
 *
 * By default shindig has handlers for the Authorization Code and Client
 * Credential flows.
 *
 */
public interface TokenEndpointResponseHandler {
  /**
   * Let the handler do it's magic including any accessor/store updates.
   *
   * If the handler is executed and encountered an error that should stop the
   * authorization process it should return the appropriate
   * {@link OAuth2HandlerError}.
   *
   *
   * Applies in particular to the client_credentials flow.
   *
   * See {@link http://tools.ietf.org/html/draft-ietf-oauth-v2-21#section-4.4.1}
   *
   * @param accessor
   * @param response
   * @return see above
   */
  public OAuth2HandlerError handleResponse(OAuth2Accessor accessor, HttpResponse response);

  /**
   * Does the handler support this {@link OAuth2Accessor} / {@link HttpResponse}
   * response?
   *
   * @param accessor
   * @param request
   * @return <code>true</code> if handleRequest() should be invoked
   */
  public boolean handlesResponse(OAuth2Accessor accessor, HttpResponse response);
}
