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

/**
 * @fileoverview Initial configuration/boot-strapping work for common container
 * to operate. This includes setting up global environment variables.
 */
gadgets.config.register('container', null, function(config) {
  var jsPath = config.container.jsPath || null;

  window.__CONTAINER_URI = shindig.uri(window.location.href);
  window.__API_URI = null;
  var scriptEl = null;
  if (window.__CONTAINER_SCRIPT_ID) {
    scriptEl = document.getElementById(window.__CONTAINER_SCRIPT_ID);
  } else {
    var scriptEls = document.getElementsByTagName('script');
    for(var i = 0; scriptEls && i < scriptEls.length; i++) {
      var src = scriptEls[i].src,
          found = jsPath != null && src && src.indexOf(jsPath) || -1;
      if(found != -1 && /.*container.*[.]js.*[?&]c=1(#|&|$).*/.test(src.substring(found + jsPath.length))) {
        scriptEl = scriptEls[i];
        break;
      }
    }
  }

  if (scriptEl) {
    window.__API_URI = shindig.uri(scriptEl.src);
    // In case script URI is relative, resolve (make absolute) with container.
    window.__API_URI.resolve(window.__CONTAINER_URI);
  }

  window.__CONTAINER = window.__API_URI ? window.__API_URI.getQP('container') : 'default';
}, false);