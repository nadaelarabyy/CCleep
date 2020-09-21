<%@page import="net.CCweb.onotogyManager"%>
<%@page import="net.CCweb.sleepClass"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.nio.file.Paths"%>
<%@page import="java.io.File"%>
<%@page import="java.net.URL"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CCleep</title>
<style type="text/css">
*, *:before, *:after {
  -moz-box-sizing: border-box;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}

body {
  font-family: 'Nunito', sans-serif;
  color: #384047;
}

form {
  max-width: 300px;
  margin: 10px auto;
  padding: 10px 20px;
  background: #f4f7f8;
  border-radius: 8px;
}

h1 {
  margin: 0 0 30px 0;
  text-align: center;
}



button {
  padding: 19px 39px 18px 39px;
  color: #FFF;
  background-color: #4bc970;
  font-size: 18px;
  text-align: center;
  font-style: normal;
  border-radius: 5px;
  width: 20%;
  border: 1px solid #3ac162;
  border-width: 1px 1px 3px;
  box-shadow: 0 -1px 0 rgba(255,255,255,0.1) inset;
  margin-bottom: 10px;
}

.item{
    text-align:center;
    display:block;
    border: 1px solid transparent;
    margin-right: 10px;
    margin-bottom: 1px;
    
  
  }
  .number {
  background-color: #5fcf80;
  color: #fff;
  height: 50px;
  width: 50px;
  display: inline-block;
  font-size: 1.0em;
  margin-right: 4px;
  /* line-height: 30px; */
  text-align: center;
  text-shadow: 0 1px 0 rgba(255,255,255,0.2);
  border-radius: 100%;
}
legend {
  font-size: 1.4em;
  margin-bottom: 10px;
  text-align: center;
}

  select {
  background: rgba(255,255,255,0.1);
  border: none;
  font-size: 16px;
  height: auto;
  margin: 0;
  outline: 0;
  padding: 15px;
  width: 100%;
  background-color: #e8eeef;
  color: #8a97a0;
  box-shadow: 0 1px 0 rgba(0,0,0,0.03) inset;
  margin-bottom: 30px;
}

select {
  padding: 6px;
  height: 32px;
  border-radius: 2px;
}

  label {
  display: block;
  margin-bottom: 8px;
}

label.light {
  font-weight: 300;
  display: inline;
}



@media screen and (min-width: 480px) {

  form {
    max-width: 600px;
  }

}
</style>
</head>
<body>
<div class="row">
    <div class="col-md-12">
      <form  method="post">
        <legend><span class="number">1</span></legend>
        <div class="item">
        <% 
        onotogyManager manager=new onotogyManager();
        //ArrayList<sleepClass> behaviorEmotion=manager.getBehaviorEmotinsNums();
    	//request.getSession().setAttribute("behaviorEmotion", behaviorEmotion);
    	 %>
          <img src="https://raw.githubusercontent.com/nadaelarabyy/Character/master/cc-logo.PNG" alt="" width="400" height="300" style="border: solid white 2px;"/>
          <div style="border: solid white 1px;">
          <h1 style="font-family: monospace;">Character Computing</h1>
          <div style="border: solid white 5px; padding: 10px;">
          <h3 >CCleep: Interaction of Character, Corona and Sleep</h3>
        </div>
      </div>
       <br>
       <label for="lang">Choose Language (اختر لغة) :</label>
          <select id="lang" name="user_lang">
            <optgroup label="Language">
              <option value="en">English</option>
              <option value="ar">عربي</option>
            </optgroup>
            
          </select>



        <button name="submitIntro" type="submit" value="submit">Next</button>
        
       </form>
        </div>
      </div>

</body>
</html>