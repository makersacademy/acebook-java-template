package com.makersacademy.acebook.model;

import com.makersacademy.acebook.repository.LikesRepository;
import com.makersacademy.acebook.repository.PostRepository;
import org.junit.Test;
import org.springframework.security.access.method.P;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.Mockito.mock;

public class LikesHandlerTest {
    private LocalDateTime testDate = LocalDateTime.now();
    CurrentUser currentUser = new CurrentUser();
    Post testPost= new Post();

    HttpServletRequest request = new HttpServletRequest() {
        @Override
        public Object getAttribute(String name) {
            return null;
        }

        @Override
        public Enumeration<String> getAttributeNames() {
            return null;
        }

        @Override
        public String getCharacterEncoding() {
            return null;
        }

        @Override
        public void setCharacterEncoding(String env) throws UnsupportedEncodingException {

        }

        @Override
        public int getContentLength() {
            return 0;
        }

        @Override
        public long getContentLengthLong() {
            return 0;
        }

        @Override
        public String getContentType() {
            return null;
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            return null;
        }

        @Override
        public String getParameter(String name) {
            return "1";
        }

        @Override
        public Enumeration<String> getParameterNames() {
            return null;
        }

        @Override
        public String[] getParameterValues(String name) {
            return new String[0];
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            return null;
        }

        @Override
        public String getProtocol() {
            return null;
        }

        @Override
        public String getScheme() {
            return null;
        }

        @Override
        public String getServerName() {
            return null;
        }

        @Override
        public int getServerPort() {
            return 0;
        }

        @Override
        public BufferedReader getReader() throws IOException {
            return null;
        }

        @Override
        public String getRemoteAddr() {
            return null;
        }

        @Override
        public String getRemoteHost() {
            return null;
        }

        @Override
        public void setAttribute(String name, Object o) {

        }

        @Override
        public void removeAttribute(String name) {

        }

        @Override
        public Locale getLocale() {
            return null;
        }

        @Override
        public Enumeration<Locale> getLocales() {
            return null;
        }

        @Override
        public boolean isSecure() {
            return false;
        }

        @Override
        public RequestDispatcher getRequestDispatcher(String path) {
            return null;
        }

        @Override
        public String getRealPath(String path) {
            return null;
        }

        @Override
        public int getRemotePort() {
            return 0;
        }

        @Override
        public String getLocalName() {
            return null;
        }

        @Override
        public String getLocalAddr() {
            return null;
        }

        @Override
        public int getLocalPort() {
            return 0;
        }

        @Override
        public ServletContext getServletContext() {
            return null;
        }

        @Override
        public AsyncContext startAsync() throws IllegalStateException {
            return null;
        }

        @Override
        public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
            return null;
        }

        @Override
        public boolean isAsyncStarted() {
            return false;
        }

        @Override
        public boolean isAsyncSupported() {
            return false;
        }

        @Override
        public AsyncContext getAsyncContext() {
            return null;
        }

        @Override
        public DispatcherType getDispatcherType() {
            return null;
        }

        @Override
        public String getAuthType() {
            return null;
        }

        @Override
        public Cookie[] getCookies() {
            return new Cookie[0];
        }

        @Override
        public long getDateHeader(String name) {
            return 0;
        }

        @Override
        public String getHeader(String name) {
            return null;
        }

        @Override
        public Enumeration<String> getHeaders(String name) {
            return null;
        }

        @Override
        public Enumeration<String> getHeaderNames() {
            return null;
        }

        @Override
        public int getIntHeader(String name) {
            return 0;
        }

        @Override
        public String getMethod() {
            return null;
        }

        @Override
        public String getPathInfo() {
            return null;
        }

        @Override
        public String getPathTranslated() {
            return null;
        }

        @Override
        public String getContextPath() {
            return null;
        }

        @Override
        public String getQueryString() {
            return null;
        }

        @Override
        public String getRemoteUser() {
            return null;
        }

        @Override
        public boolean isUserInRole(String role) {
            return false;
        }

        @Override
        public Principal getUserPrincipal() {
            return null;
        }

        @Override
        public String getRequestedSessionId() {
            return null;
        }

        @Override
        public String getRequestURI() {
            return null;
        }

        @Override
        public StringBuffer getRequestURL() {
            return null;
        }

        @Override
        public String getServletPath() {
            return null;
        }

        @Override
        public HttpSession getSession(boolean create) {
            return null;
        }

        @Override
        public HttpSession getSession() {
            return null;
        }

        @Override
        public String changeSessionId() {
            return null;
        }

        @Override
        public boolean isRequestedSessionIdValid() {
            return false;
        }

        @Override
        public boolean isRequestedSessionIdFromCookie() {
            return false;
        }

        @Override
        public boolean isRequestedSessionIdFromURL() {
            return false;
        }

        @Override
        public boolean isRequestedSessionIdFromUrl() {
            return false;
        }

        @Override
        public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
            return false;
        }

        @Override
        public void login(String username, String password) throws ServletException {

        }

        @Override
        public void logout() throws ServletException {

        }

        @Override
        public Collection<Part> getParts() throws IOException, ServletException {
            return null;
        }

        @Override
        public Part getPart(String name) throws IOException, ServletException {
            return null;
        }

        @Override
        public <T extends HttpUpgradeHandler> T upgrade(Class<T> httpUpgradeHandlerClass) throws IOException, ServletException {
            return null;
        }
    };
//    PostRepository repository = mock(PostRepository.class);
//    LikesRepository likesRepository = mock(LikesRepository.class);
//    LikesHandler likesHandler = new LikesHandler(repository, likesRepository, currentUser);
PostRepository repository= new PostRepository() {
    @Override
    public <S extends Post> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Post> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Post> findById(Long aLong) {
        testPost.populateMock(aLong,"blaa",testDate,"john",0);
        return Optional.of(testPost);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Post> findAll() {
        return null;
    }

    @Override
    public Iterable<Post> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Post entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Post> entities) {

    }

    @Override
    public void deleteAll() {

    }
};

LikesRepository likesRepository= new LikesRepository() {
    @Override
    public <S extends Like> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Like> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Like> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Like> findAll() {
        return null;
    }

    @Override
    public Iterable<Like> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Like entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Like> entities) {

    }

    @Override
    public void deleteAll() {

    }
};

LikesHandler likesHandler= new LikesHandler(repository,likesRepository);
RedirectAttributes redirect= new RedirectAttributes() {
    @Override
    public RedirectAttributes addAttribute(String attributeName, Object attributeValue) {
        return null;
    }

    @Override
    public RedirectAttributes addAttribute(Object attributeValue) {
        return null;
    }

    @Override
    public RedirectAttributes addAllAttributes(Collection<?> attributeValues) {
        return null;
    }

    @Override
    public RedirectAttributes mergeAttributes(Map<String, ?> attributes) {
        return null;
    }

    @Override
    public RedirectAttributes addFlashAttribute(String attributeName, Object attributeValue) {
        return null;
    }

    @Override
    public RedirectAttributes addFlashAttribute(Object attributeValue) {
        return null;
    }

    @Override
    public Map<String, ?> getFlashAttributes() {
        return null;
    }

    @Override
    public Model addAllAttributes(Map<String, ?> attributes) {
        return null;
    }

    @Override
    public boolean containsAttribute(String attributeName) {
        return false;
    }

    @Override
    public Map<String, Object> asMap() {
        return null;
    }
};
//    @Test
//    public void hasLikes() {
//        Post testPost = new Post();
//        testPost.populate_mock((long) 1, "a", testDate, "yoyo", 0);
//        when(request.getParameter(any())).thenReturn("1");
//
//
//        likesHandler.handleLike(request);
//
//    }

    @Test
    public void hasLikes(){
//    likesHandler.liked(request,redirect,username);
    }


}
