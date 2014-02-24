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

import org.apache.shindig.gadgets.http.HttpRequest;
import org.apache.shindig.gadgets.oauth2.OAuth2Accessor;
import org.apache.shindig.gadgets.oauth2.OAuth2Error;
import org.apache.shindig.gadgets.oauth2.OAuth2Message;

import org.apache.commons.codec.binary.Base64;

/**
 *
 * See {@link ClientAuthenticationHandler}
 *
 * Handler for Basic Authentication
 *
 */
public class BasicAuthenticationHandler implements ClientAuthenticationHandler {
  private static final OAuth2Error ERROR = OAuth2Error.AUTHENTICATION_PROBLEM;

  public OAuth2HandlerError addOAuth2Authentication(final HttpRequest request,
          final OAuth2Accessor accessor) {
    try {
      if (request == null) {
        return BasicAuthenticationHandler.getError("request is null");
      }

      if (accessor == null || !accessor.isValid() || accessor.isErrorResponse()) {
        return BasicAuthenticationHandler.getError("accessor is invalid " + accessor);
      }

      final String clientId = accessor.getClientId();

      if (clientId == null) {
        return BasicAuthenticationHandler.getError("client_id is null");
      }

      final byte[] secretBytes = accessor.getClientSecret();

      if (secretBytes == null) {
        return BasicAuthenticationHandler.getError("client_secret is secret");
      }

      final String secret = new String(secretBytes, "UTF-8");

      final String authString = clientId + ':' + secret;
      final byte[] authBytes = Base64.encodeBase64(authString.getBytes());
      request.setHeader(OAuth2Message.AUTHORIZATION_HEADER, "Basic: " + new String(authBytes));

      return null;
    } catch (final Exception e) {
      return BasicAuthenticationHandler.getError("Exception adding basic auth headers", e);
    }
  }

  public String geClientAuthenticationType() {
    return OAuth2Message.BASIC_AUTH_TYPE;
  }

  private static OAuth2HandlerError getError(final String contextMessage) {
    return BasicAuthenticationHandler.getError(contextMessage, null);
  }

  private static OAuth2HandlerError getError(final String contextMessage, final Exception e) {
    return new OAuth2HandlerError(BasicAuthenticationHandler.ERROR, contextMessage, e);
  }
}
