package servlets;

import datastore.DAOSQLite;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Parmor;

/**
 * All of this application's web pages send their requests to this controller
 * which then updates the model / database as needed and transfers control with
 * data to one the the HTML/JSP view-oriented programs for display.
 *
 * @author Jon VanZile - modified from John Phillips' code
 * @version 0.1 on 2015-12-01
 */
public class Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get real path to the sqlite db
        ServletContext sc = this.getServletContext();
        String dbPath = sc.getRealPath("/WEB-INF/superstar.db");

        // set default url
        String url = "/home.html";

        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "home";
        }

        // perform action and set url
        if (action.equalsIgnoreCase("home")) {
            url = "/home.html";

        } else if (action.equalsIgnoreCase("createRecord")) {
            int dResist = 0;
            int eResist = 0;
            int rResist = 0;
            
            // get parameters passed in from the request
            String modelName = request.getParameter("modelName");
            String slot = request.getParameter("slot");
            String paint = request.getParameter("paint");
            String dResistString = request.getParameter("dResist");
            String eResistString = request.getParameter("eResist");
            String rResistString = request.getParameter("rResist");
            String location = request.getParameter("location");
            // validate and convert salary string into a double
            if (dResistString == null || dResistString.isEmpty()) {
                dResist = 0;
            } else {
                dResist = Integer.parseInt(dResistString);
            }
            
            if (eResistString == null || eResistString.isEmpty()) {
                eResist = 0;
            } else {
                eResist = Integer.parseInt(eResistString);
            }
            
            if (rResistString == null || rResistString.isEmpty()) {
                rResist = 0;
            } else {
                rResist = Integer.parseInt(rResistString);
            }

            // store data in an Parmor object
            Parmor parmor = new Parmor(0, modelName, slot, paint, dResist, eResist, rResist, location);
            
            // validate the parameters
            if (modelName == null || slot == null || paint == null
                    || location == null || modelName.isEmpty()
                    || slot.isEmpty() || paint.isEmpty()
                    || location.isEmpty()) {
                url = "/createRecord.jsp";
            } else {
                // insert this data record into the database
                DAOSQLite.createRecord(parmor, dbPath);
                url = "/home.html";
            }

            // add the user object to the request object so that the data is available on the next page
            //request.setAttribute("employee", employee);
        } else if (action.equalsIgnoreCase("report")) {
            List<Parmor> mydata = null;
            mydata = DAOSQLite.retrieveAllRecordsByName(dbPath);
            request.setAttribute("mydata", mydata);
            url = "/displayRecords.jsp";

        } else if (action.equalsIgnoreCase("updateRecord1")) {
            String idString = request.getParameter("id");
            if (idString == null || idString.isEmpty()) {
                url = "/updateRecord1.jsp";
            } else {
                // get employee
                //  User employee = DAOSQLite.retrieveRecordById(Integer.parseInt(idString), dbPath);
                // request.setAttribute("employee", employee);
                url = "/updateRecord2.jsp";
            }

        } else if (action.equalsIgnoreCase("updateRecord2")) {
            int empId;
            double salary;

            // get parameters passed in from the request
            String empIdString = request.getParameter("empId");
            String lastName = request.getParameter("lastName");
            String firstName = request.getParameter("firstName");
            String homePhone = request.getParameter("homePhone");
            String salaryString = request.getParameter("salary");

            // validate and convert empId string into an int
            if (empIdString == null || empIdString.isEmpty()) {
                empId = 0;
            } else {
                empId = Integer.parseInt(empIdString);
            }

            // validate and convert salary string into a double
            if (salaryString == null || salaryString.isEmpty()) {
                salary = 0;
            } else {
                salary = Double.parseDouble(salaryString);
            }

            // store data in an User object
            // User employee = new User(empId, lastName, firstName, homePhone, salary);
            // validate the parameters
            if (empIdString == null || lastName == null || firstName == null
                    || homePhone == null || salaryString == null
                    || empIdString.isEmpty() || lastName.isEmpty()
                    || firstName.isEmpty() || homePhone.isEmpty()
                    || salaryString.isEmpty()) {
                //    request.setAttribute("employee", employee);
                url = "/updateRecord2.jsp";
            } else {
                // update this record in the database
                //    DAOSQLite.updateRecord(employee, dbPath);
                url = "/home.html";
            }

        } else if (action.equalsIgnoreCase("deleteRecord")) {
            String idString = request.getParameter("id");
            if (idString == null || idString.isEmpty()) {
                url = "/deleteRecord.jsp";
            } else {
                // delete this data record from the database
                DAOSQLite.deleteRecord(Integer.parseInt(idString), dbPath);
                url = "/home.html";
            }

        } else if (action.equalsIgnoreCase("makeDB")) {
            DAOSQLite.dropTable(dbPath);
            DAOSQLite.createTable(dbPath);
            DAOSQLite.populateTable(dbPath);
            url = "/home.html";
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);

        dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Controller for Employee App";
    }// </editor-fold>

}
