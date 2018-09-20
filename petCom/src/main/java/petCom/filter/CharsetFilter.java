package petCom.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "de",urlPatterns = {"/*"})
public class CharsetFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("filter ini");
    }


    @Override
    public void destroy() {
        System.out.println("filter des");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
            IOException,ServletException{
        System.out.println("filter do");
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}