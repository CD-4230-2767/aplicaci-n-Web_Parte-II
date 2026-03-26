<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <title>Murach's Java Servlets and JSP</title>
        <!-- Latest compiled and minidield css -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"> 
        
        <!-- Latest compiled JavaScript --> 
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script> 
    </head>
    <body>
        <div class='container'> 
            <div class='row'> 
                <div class='col'> 
                    <h1 class='container'>Thanks for joining our email list</h1>                     
                </div> 
            </div> 
        </div> 
 
        <div class='container'> 
            <div class='row'> 
                <div class='col'> 
                    <p>Here is the information that you entered:</p> 
 
                    <label>Email:</label> 
                    <span>${user.email}</span><br> 
                    <label>Fist Name:</label> 
                    <span>${user.firstName}</span><br> 
                    <label>Last Name:</label> 
                    <span>${user.lastName}</span><br> 
 
                    <p>To enter another email address, click on the Back<br> 
                        button  in your browser or the Return button show below.</p> 
                     
                    <form action="" method="get"> 
                        <input type="hidden" name="action" value="join"> 
                        <input type="submit" class="btn btn-primary" value="Return"> 
                    </form> 
                </div> 
            </div> 
        </div> 
    </body>
</html>
