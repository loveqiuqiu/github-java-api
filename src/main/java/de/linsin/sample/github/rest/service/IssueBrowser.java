package de.linsin.sample.github.rest.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.linsin.sample.github.rest.domain.Issue;
import de.linsin.sample.github.rest.domain.IssuesResponse;
import de.linsin.sample.github.rest.domain.Repository;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Provides methods to browser through GitHub issues for a particular repository.
 * You need your GitHub credentials, in order to use certain methods
 *
 * @author David Linsin - linsin@synyx.de
 */
public class IssueBrowser {
    public static final String BASE_URL = "http://github.com/api/v2/json/";
    public static final String ISSUES_BASE_URL = BASE_URL.concat("issues/list/{username}/{repo}");
    public static final String ISSUE_BASE_URL = BASE_URL.concat("issues/show/{username}/{repo}");
    public static final String ISSUES_OPEN_URL = ISSUES_BASE_URL.concat("/open");
    public static final String ISSUES_CLOSED_URL = ISSUES_BASE_URL.concat("/closed");
    public static final String ISSUE_URL = ISSUES_BASE_URL.concat("/{no}");

    public static final String OPEN_ISSUE_URL = BASE_URL.concat("issues/open/{username}/{repo}");
    public static final String CLOSE_ISSUE_URL = BASE_URL.concat("issues/close/{username}/{repo}/{no}");
    public static final String REOPEN_ISSUE_URL = BASE_URL.concat("issues/reopen/{username}/{repo}/{no}");
    public static final String EDIT_ISSUE_URL = BASE_URL.concat("issues/edit/{username}/{repo}/{no}");

    public static final String COMMENT_ISSUE_URL = BASE_URL.concat("issues/comment/{username}/{repo}/{no}");

    private String apiToken;
    private String username;

    /**
     * Initializes invariables of IssueBrowser
     *
     * @param argUsername {@link String} username of the callee
     * @param argApiToken {@link String} api token that you need in order to call GitHub
     */
    public IssueBrowser(String argUsername, String argApiToken) {
        apiToken = argApiToken;
        username = argUsername;
    }

    /**
     * Use this constructor in case you don't want to authorize.
     * Note that various methods need authorization.
     */
    public IssueBrowser() {
    }

    /**
     * Browse open issues of a repository
     *
     * @param argRepository {@link Repository} instance which contains name and owner
     * @return {@link List} containing {@link Issues} instances, empty List if no open issues are found
     * @throws a {@link NullPointerException} in case passed repository is null
     */

    public List<Issue> browseOpen(Repository argRepository) {
        RestTemplate template = initTemplate();
        IssuesResponse resp = template.getForObject(ISSUES_OPEN_URL, IssuesResponse.class, argRepository.getOwner(), argRepository.getName());
        if (resp == null || resp.getIssues() == null || resp.getIssues().length == 0) {
            return Collections.emptyList();
        } else {
            return Arrays.asList(resp.getIssues());
        }
    }

    protected RestTemplate initTemplate() {
        RestTemplate template = new RestTemplate();
        template.setMessageConverters(new HttpMessageConverter[]{new MappingJacksonHttpMessageConverter()});
        return template;
    }
}