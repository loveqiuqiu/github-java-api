/*
 * Copyright 2009 David Linsin
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.linsin.github.rest.domain;

/**
 * Represents a request for an issue
 *
 * @author David Linsin - dlinsin@gmail.com
 */
public class IssueRequest {
    private String login;
    private String token;

    public IssueRequest(String argLogin, String argToken) {
        login = argLogin;
        token = argToken;
    }

    public IssueRequest() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String argLogin) {
        login = argLogin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String argToken) {
        token = argToken;
    }

}