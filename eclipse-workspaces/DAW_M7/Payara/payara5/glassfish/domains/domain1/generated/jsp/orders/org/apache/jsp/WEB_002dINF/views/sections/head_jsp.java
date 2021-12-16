package org.apache.jsp.WEB_002dINF.views.sections;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class head_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("<link rel=\"stylesheet\"\n");
      out.write("\thref=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" />\n");
      out.write("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\n");
      out.write("<script\n");
      out.write("\tsrc=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n");
      out.write("<title>Orders</title>\n");
      out.write("<style>\n");
      out.write(".form-control-error {\n");
      out.write("\tborder-color: #a94442;\n");
      out.write("\t-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);\n");
      out.write("\tbox-shadow: inset 0 1px 1px rgba(0, 0, 0, .075)\n");
      out.write("}\n");
      out.write("\n");
      out.write(".form-control-error:focus {\n");
      out.write("\tborder-color: #843534;\n");
      out.write("\t-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 6px #ce8483;\n");
      out.write("\tbox-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 6px #ce8483\n");
      out.write("}\n");
      out.write("\n");
      out.write(".input-group-addon-error {\n");
      out.write("\tcolor: #a94442;\n");
      out.write("\tbackground-color: #f2dede;\n");
      out.write("\tborder-color: #a94442\n");
      out.write("}\n");
      out.write("\n");
      out.write(".control-label-error {\n");
      out.write("\tcolor: #a94442\n");
      out.write("}\n");
      out.write("</style>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
