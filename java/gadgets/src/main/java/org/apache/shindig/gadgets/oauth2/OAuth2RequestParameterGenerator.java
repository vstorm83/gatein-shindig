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
package org.apache.shindig.gadgets.oauth2;

import org.apache.shindig.gadgets.http.HttpRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Enables injection of a new request parameter generator into the system.
 */
public interface OAuth2RequestParameterGenerator {

  /**
   * Generates additional parameters that are added to the request sent to the authorization server
   *
   * @return map of additional parameters. this should never be <code>null</code>
   */
  public Map<String, String> generateParams(HttpRequest request);
}
