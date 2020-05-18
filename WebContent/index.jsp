<%@page import="net.CCweb.sleepClass"%>
<%@page import="net.CCweb.ontologyClass"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.CCweb.onotogyManager"%>
<%@page import="org.semanticweb.owlapi.reasoner.OWLReasoner"%>
<%@page import="org.semanticweb.owlapi.model.OWLOntology"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" >
<title>Insert title here</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
form {
  max-width: 800px;
  margin: 10px auto;
  padding: 10px 20px;
  background: #f4f7f8;
  border-radius: 8px;
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

.collapsible {
  background-color: #777;
  color: white;
  cursor: pointer;
  padding: 18px;
  width: 100%;
  border: none;
  text-align: left;
  outline: none;
  font-size: 15px;
}

.active, .collapsible:hover {
  background-color: #555;
}

.content {
  padding: 0 18px;
  display: none;
  overflow: hidden;
  background-color: #f1f1f1;
  
}
.toggle-box {
  display: none;
}

.toggle-box + label {
  cursor: pointer;
  display: block;
  font-weight: bold;
  line-height: 21px;
  margin-bottom: 5px;
}

.toggle-box + label + div {
  display: none;
  margin-bottom: 10px;
}

.toggle-box:checked + label + div {
  display: block;
}

.toggle-box + label:before {
  background-color: #4F5150;
  -webkit-border-radius: 10px;
  -moz-border-radius: 10px;
  border-radius: 10px;
  color: #FFFFFF;
  content: "+";
  display: block;
  float: left;
  font-weight: bold;
  height: 20px;
  line-height: 20px;
  margin-right: 5px;
  text-align: center;
  width: 20px;
}


.container {
    max-width: 640px;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    font-size: 13px;
}

ul.ks-cboxtags {
    list-style: none;
    padding: 20px;
}
ul.ks-cboxtags li{
  display: inline;
}
ul.ks-cboxtags li label{
    display: inline-block;
    background-color: rgba(255, 255, 255, .9);
    border: 2px solid rgba(139, 139, 139, .3);
    color: #adadad;
    border-radius: 25px;
    white-space: nowrap;
    margin: 3px 0px;
    -webkit-touch-callout: none;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    -webkit-tap-highlight-color: transparent;
    transition: all .2s;
}

ul.ks-cboxtags li label {
    padding: 8px 12px;
    cursor: pointer;
}

ul.ks-cboxtags li label::before {
    display: inline-block;
    font-style: normal;
    font-variant: normal;
    text-rendering: auto;
    -webkit-font-smoothing: antialiased;
    font-family: "Font Awesome 5 Free";
    font-weight: 900;
    font-size: 12px;
    padding: 2px 6px 2px 2px;
    transition: transform .3s ease-in-out;
}

ul.ks-cboxtags li input[type="checkbox"]:checked + label::before {
    transform: rotate(-360deg);
    transition: transform .3s ease-in-out;
}

ul.ks-cboxtags li input[type="checkbox"]:checked + label {
    border: 2px solid #1bdbf8;
    background-color: #12bbd4;
    color: #fff;
    transition: all .2s;
}

ul.ks-cboxtags li input[type="checkbox"] {
  display: absolute;
}
ul.ks-cboxtags li input[type="checkbox"] {
  position: absolute;
  opacity: 0;
}
ul.ks-cboxtags li input[type="checkbox"]:focus + label {
  border: 2px solid #e9a1ff;
}
.accordion {
  background-color: #eee;
  color: #444;
  cursor: pointer;
  padding: 18px;
  width: 100%;
  border: none;
  text-align: left;
  outline: none;
  font-size: 15px;
  transition: 0.4s;
}

.active, .accordion:hover {
  background-color: #ccc; 
}

.panel {
  padding: 0 18px;
  display: none;
  background-color: white;
  overflow: hidden;
}


<!-- ------------------------------------------------------------------------------------------->
[type=text],
[type=email],
[type=url],
select,
textarea {
  display: block;
  padding: .5rem;
  background: transparent;
  vertical-align: middle;
  width: 100%;
  max-width: 100%;
  border: 1px solid #cdcdcd;
  border-radius: 4px;
  font-size: .95rem;
}

[type=text]:focus,
[type=email]:focus,
[type=url]:focus,
select:focus,
textarea:focus {
  outline: none;
  border: 1px solid #1E6BD6;
} 

select {
  -webkit-appearance: none;
  -moz-appearance: none;
  background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABIAAAAJCAYAAAA/33wPAAAAvklEQVQoFY2QMQqEMBBFv7ERa/EMXkGw11K8QbDXzuN4BHv7QO6ifUgj7v4UAdlVM8Uwf+b9YZJISnlqrfEUZVlinucnBGKaJgghbiHOyLyFKIoCbdvecpyReYvo/Ma2bajrGtbaC58kCdZ1RZ7nl/4/4d5EsO/7nzl7IUtodBexMMagaRrs+06JLMvcNWmaOv2W/C/TMAyD58dxROgSmvxFFMdxoOs6lliWBXEcuzokXRbRoJRyvqqqQvye+QDMDz1D6yuj9wAAAABJRU5ErkJggg==) 100% no-repeat;
  line-height: 1
}

label {
  font-weight: 600;
  font-size: .9rem;
  display: block;
  margin: .5rem 0;
}
.container {
  max-width: 600px;
  margin: 0 auto;
  padding: 0 1rem;
}


/* Button */

[type=submit] {
  display: inline-block;
  vertical-align: middle;
  white-space: nowrap;
  cursor: pointer;
  margin: .25rem 0;
  padding: .5rem 1rem;
  border: 1px solid #4bc970;
  border-radius: 18px;
  background: #4bc970;
  color: white;
  font-weight: 600;
  text-decoration: none;
  font-family: sans-serif;
  font-size: .95rem;
}

<!---------------------------------------------- -->





li {
  position: relative;
  display: inline-block;
  border-bottom: 1px dotted black;
}

li .tooltiptext {
  visibility: hidden;
  max-width: 300px;
  background-color: black;
  color: #fff;
  text-align: center;
  border-radius: 6px;
  padding: 5px 0;
  
  /* Position the tooltip */
  position: absolute;
  z-index: 1;

 
}

li:hover .tooltiptext {
  visibility: visible;
}




  #loader { 
            border: 12px solid #f3f3f3; 
            border-radius: 50%; 
            border-top: 12px solid #444444; 
            width: 70px; 
            height: 70px; 
            animation: spin 1s linear infinite; 
        } 
          
        @keyframes spin { 
            100% { 
                transform: rotate(360deg); 
            } 
        } 
          
        .center { 
            position: absolute; 
            top: 0; 
            bottom: 0; 
            left: 0; 
            right: 0; 
            margin: auto; 
        } 



</style>



</head>
<body>
<div id="loader" class="center"></div>
  <div class="row">
    <div class="col-md-12">
<form method="post" >
   <legend><span class="number">2</span></legend>
   
   <div style="text-align: center">
             <img src="https://raw.githubusercontent.com/nadaelarabyy/Character/master/CBSTriad.PNG" alt="" width="300" height="300" style="border: solid white 2px;"/>
             
   </div>
  <h1 style="font-family: monospace;"></h1>
  <%String lang=(String)request.getSession().getAttribute("lang"); %>
     <div style="border: solid white 2px; font-family: monospace;">
       <h3>
                <%=lang.equals("en")?"In C-B-S triad we are mapping character, behavior and situation and by knowing two parts of the triad we can infer the third one so imagine having a certain behavior in specific situation then we can infer character <br> Behavior: performing certain activity, food consumption, emotions <br> Situation: couple of hours before going to bed <br> Character: personality traits based on the big five model":"التصرفات: النشاطات التي تقوم بها <br> الموقف: الساعات الاخيرة قبل الذهاب للنوم <br> الشخصية: صفات الانسان "%>
       
       </h3>
     </div>
    <label for="email"><%=lang.equals("en")?"Email":"حساب البريد"%></label>
    <input type="email" name="email" id="email" placeholder="Email Address">
    <label for="gender"><%=lang.equals("en")?"Gender":"النوع"%></label>
    <select name="gender" id="gender">
      <option value="male"><%=lang.equals("en")?"Male":"ذكر"%></option>
      <option value="female"><%=lang.equals("en")?"Female":"انثي"%></option>
      <option value="female"><%=lang.equals("en")?"other":"اخر"%></option>
    </select>
    <label for="age"><%=lang.equals("en")?"Age":"السن" %></label>
    <input type="number" name="age" id="age" placeholder="age">
    <br>
    <br>
    <hr>
    <br>
    <div>
    
    
    <button type="button" class="collapsible">
    <%=lang.equals("en")?"Which of these food products do you consume within the couples of hours before going to bed?":"أي من هذه الاطعمة تتناولها في الساعات الاخيرة من اللليل قبل الذهاب للنوم؟"%>
    
    
    </button>
<div class="content">
  
<!-- ------------------vegetabes-------------------- -->
        <br>
<input class="toggle-box" id="identifier-1" type="checkbox">
<label for="identifier-1"><%=lang.equals("en")?"Vegetables":"خضروات" %></label>

<div>
<%
onotogyManager manager=new onotogyManager();

OWLOntology ontology=manager.loadOntology();
OWLReasoner reasoner=manager.useReasoner(ontology);
//ArrayList<sleepClass> foodActivity=manager.getActivityFoodNums(ontology,reasoner);

//request.getSession().setAttribute("foodActivity", foodActivity);
request.getSession().setAttribute("persoanlityList", manager.getPersonality(ontology, reasoner));
ArrayList<ontologyClass> vegetablesArray=manager.getVegetables(ontology, reasoner);
		for(int i=0;vegetablesArray.size()>i;i++){
%>
<button type="button" class="accordion"><%=lang.equals("en")?vegetablesArray.get(i).getClassName_en():vegetablesArray.get(i).getClassName_ar() %></button>
<div class="panel">
<!-- ------------------------------------------------------------------------------- -->
  <div class="container">
    <ul class="ks-cboxtags">
    <%
    
    ArrayList<ontologyClass> vegetablesInstances=vegetablesArray.get(i).getInstances();
    for(int j=0;vegetablesInstances.size()>j;j++){
    %>
      <li>
      <input type="checkbox" name="food" id=<%="vegetables"+i+"_"+j %> value=<%=vegetablesInstances.get(j).getClassName_en().replace(" ", "_")+"$"+vegetablesInstances.get(j).getIriShortForm() %>>
      <label for=<%="vegetables"+i+"_"+j %>><%=lang.equals("en")?vegetablesInstances.get(j).getClassName_en():vegetablesInstances.get(j).getClassName_ar() %></label>
      <span class="tooltiptext"><%=vegetablesInstances.get(j).getDefinition().equals("none")?vegetablesInstances.get(j).getComment():(vegetablesInstances.get(j).getDefinition().equals("none")?"":vegetablesInstances.get(j).getDefinition()) %></span>
      
      </li>
     
      <%} %>
    </ul>
  
  </div>
  <!-- ------------------------------------------------------------------------------- -->
  
</div>
<%} %>




</div>




<!-- ---------------Desserts and Sweet Food-------------------------- -->
        <br>
<input class="toggle-box" id="identifier-2" type="checkbox">
<label for="identifier-2">
<%=lang.equals("en")?"Sweet Food and Desserts":"الحلويات" %>

</label>

<div>
<%

ArrayList<ontologyClass> sweetFoodArray=manager.getSweetFood(ontology, reasoner);
		for(int i=0;sweetFoodArray.size()>i;i++){
%>
<button type="button" class="accordion"><%=lang.equals("en")?sweetFoodArray.get(i).getClassName_en():sweetFoodArray.get(i).getClassName_ar() %></button>
<div class="panel">
<!-- ------------------------------------------------------------------------------- -->
  <div class="container">
    <ul class="ks-cboxtags">
    <%
    ArrayList<ontologyClass> sweetFoodInstances=sweetFoodArray.get(i).getInstances();
    for(int j=0;sweetFoodInstances.size()>j;j++){
    %>
      <li>
      <input type="checkbox" name="food" id=<%="sweetFood"+i+"_"+j %> value=<%=sweetFoodInstances.get(j).getClassName_en().replace(" ", "_")+"$"+sweetFoodInstances.get(j).getIriShortForm() %>>
      <label for=<%="sweetFood"+i+"_"+j %>><%=lang.equals("en")?sweetFoodInstances.get(j).getClassName_en():sweetFoodInstances.get(j).getClassName_ar() %></label>
      <span class="tooltiptext"><%=sweetFoodInstances.get(j).getDefinition().equals("none")?sweetFoodInstances.get(j).getComment():(sweetFoodInstances.get(j).getDefinition().equals("none")?"":sweetFoodInstances.get(j).getDefinition()) %></span>
      </li>
     
      <%} %>
    </ul>
  
  </div>
  <!-- ------------------------------------------------------------------------------- -->
  
</div>
<%} %>




</div>

<!-- ------------------Sausages----------------------- -->
        <br>
        <input class="toggle-box" id="identifier-3" type="checkbox">
<label for="identifier-3"><%=lang.equals("en")?"Sausages":"النقانق" %></label>

<div>
<%
ArrayList<ontologyClass> SausagesArray=manager.getSausages(ontology, reasoner);
		for(int i=0;SausagesArray.size()>i;i++){
%>
<button type="button" class="accordion"><%=lang.equals("en")?SausagesArray.get(i).getClassName_en():SausagesArray.get(i).getClassName_ar() %></button>
<div class="panel">
<!-- ------------------------------------------------------------------------------- -->
  <div class="container">
    <ul class="ks-cboxtags">
    <%
    ArrayList<ontologyClass> sausageInstances=SausagesArray.get(i).getInstances();
    for(int j=0;sausageInstances.size()>j;j++){
    %>
      <li>
      <input type="checkbox" name="food" id=<%="sausages"+i+"_"+j %> value=<%=sausageInstances.get(j).getClassName_en().replace(" ", "_")+"$"+sausageInstances.get(j).getIriShortForm() %>>
      <label for=<%="sausages"+i+"_"+j %>><%=lang.equals("en")?sausageInstances.get(j).getClassName_en():sausageInstances.get(j).getClassName_ar() %></label>
      <span class="tooltiptext"><%=sausageInstances.get(j).getDefinition().equals("none")?sausageInstances.get(j).getComment():(sausageInstances.get(j).getDefinition().equals("none")?"":sausageInstances.get(j).getDefinition()) %></span>
      </li>
     
      <%} %>
    </ul>
  
  </div>
  <!-- ------------------------------------------------------------------------------- -->
  
</div>
<%} %>




</div>

<!-- --------------------Nuts and seeds--------------------- -->
        <br>
        <input class="toggle-box" id="identifier-4" type="checkbox">
<label for="identifier-4"><%=lang.equals("en")?"Nuts seeds and olives":"المكسرات البذور و الزيتون" %> </label>

<div>
<%
ArrayList<ontologyClass> nutArray=manager.getNutsAndSeeds(ontology, reasoner);
		for(int i=0;nutArray.size()>i;i++){
%>
<button type="button" class="accordion"><%=lang.equals("en")?nutArray.get(i).getClassName_en():nutArray.get(i).getClassName_ar() %></button>
<div class="panel">
<!-- ------------------------------------------------------------------------------- -->
  <div class="container">
    <ul class="ks-cboxtags">
    <%
    ArrayList<ontologyClass> nutInstances=nutArray.get(i).getInstances();
    for(int j=0;nutInstances.size()>j;j++){
    %>
      <li>
      <input type="checkbox" name="food" id=<%="nuts"+i+"_"+j %> value=<%=nutInstances.get(j).getClassName_en().replace(" ", "_")+"$"+nutInstances.get(j).getIriShortForm() %>>
      <label for=<%="nuts"+i+"_"+j %>><%=lang.equals("en")?nutInstances.get(j).getClassName_en():nutInstances.get(j).getClassName_ar() %></label>
      <span class="tooltiptext"><%=nutInstances.get(j).getDefinition().equals("none")?nutInstances.get(j).getComment():(nutInstances.get(j).getDefinition().equals("none")?"":nutInstances.get(j).getDefinition()) %></span>
      </li>
     
      <%} %>
    </ul>
  
  </div>
  <!-- ------------------------------------------------------------------------------- -->
  
</div>
<%} %>

</div>

<!-- -------------Dairy and milk products---------------------------- -->
        <br>
        <input class="toggle-box" id="identifier-5" type="checkbox">
<label for="identifier-5"><%=lang.equals("en")?"Dairy and milk products":"منتجات الالبان" %></label>

<div>
<%
ArrayList<ontologyClass> dairyArray=manager.getDairyProducts(ontology, reasoner);
		for(int i=0;dairyArray.size()>i;i++){
%>
<button type="button" class="accordion"><%=lang.equals("en")?dairyArray.get(i).getClassName_en():dairyArray.get(i).getClassName_ar() %></button>
<div class="panel">
<!-- ------------------------------------------------------------------------------- -->
  <div class="container">
    <ul class="ks-cboxtags">
    <%
    ArrayList<ontologyClass> dairyInstances=dairyArray.get(i).getInstances();
    for(int j=0;dairyInstances.size()>j;j++){
    %>
      <li>
      <input type="checkbox" name="food" id=<%="dairy"+i+"_"+j %> value=<%=dairyInstances.get(j).getClassName_en().replace(" ", "_")+"$"+dairyInstances.get(j).getIriShortForm() %>>
      <label for=<%="dairy"+i+"_"+j %>><%=lang.equals("en")?dairyInstances.get(j).getClassName_en():dairyInstances.get(j).getClassName_ar() %></label>
      <span class="tooltiptext"><%=dairyInstances.get(j).getDefinition().equals("none")?dairyInstances.get(j).getComment():(dairyInstances.get(j).getDefinition().equals("none")?"":dairyInstances.get(j).getDefinition()) %></span>
      </li>
     
      <%} %>
    </ul>
  
  </div>
  <!-- ------------------------------------------------------------------------------- -->
  
</div>
<%} %>

</div>
<!-- ----------------------------------------- -->
        <br>
                <input class="toggle-box" id="identifier-6" type="checkbox">
<label for="identifier-6"><%=lang.equals("en")?"Fruit":"فواكهة" %></label>

<div>
<%
ArrayList<ontologyClass> fruitArray=manager.getFruits(ontology, reasoner);
		for(int i=0;fruitArray.size()>i;i++){
%>
<button type="button" class="accordion"><%=lang.equals("en")?fruitArray.get(i).getClassName_en():fruitArray.get(i).getClassName_ar() %></button>
<div class="panel">
<!-- ------------------------------------------------------------------------------- -->
  <div class="container">
    <ul class="ks-cboxtags">
    <%
    ArrayList<ontologyClass> fruitInstances=fruitArray.get(i).getInstances();
    for(int j=0;fruitInstances.size()>j;j++){
    %>
      <li>
      <input type="checkbox" name="food" id=<%="fruit"+i+"_"+j %> value=<%=fruitInstances.get(j).getClassName_en().replace(" ", "_")+"$"+fruitInstances.get(j).getIriShortForm() %>>
      <label for=<%="fruit"+i+"_"+j %>><%=lang.equals("en")?fruitInstances.get(j).getClassName_en():fruitInstances.get(j).getClassName_ar() %></label>
      <span class="tooltiptext"><%=fruitInstances.get(j).getDefinition().equals("none")?fruitInstances.get(j).getComment():(fruitInstances.get(j).getDefinition().equals("none")?"":fruitInstances.get(j).getDefinition()) %></span>
      </li>
     
      <%} %>
    </ul>
  
  </div>
  <!-- ------------------------------------------------------------------------------- -->
  
</div>
<%} %>

</div>

<!-- ----------------------------------------- -->
        <br>
<input class="toggle-box" id="identifier-7" type="checkbox">
<label for="identifier-7"><%=lang.equals("en")?"Eggs":"البيض" %></label>

<div>
<%
ArrayList<ontologyClass> eggArray=manager.getEggs(ontology,reasoner);
		for(int i=0;eggArray.size()>i;i++){
%>
<button type="button" class="accordion"><%=lang.equals("en")?eggArray.get(i).getClassName_en():eggArray.get(i).getClassName_ar() %></button>
<div class="panel">
<!-- ------------------------------------------------------------------------------- -->
  <div class="container">
    <ul class="ks-cboxtags">
    <%
    ArrayList<ontologyClass> eggInstances=eggArray.get(i).getInstances();
    for(int j=0;eggInstances.size()>j;j++){
    %>
      <li>
      <input type="checkbox" name="food" id=<%="egg"+i+"_"+j %> value=<%=eggInstances.get(j).getClassName_en().replace(" ", "_")+"$"+eggInstances.get(j).getIriShortForm() %>>
      <label for=<%="egg"+i+"_"+j %>><%=lang.equals("en")?eggInstances.get(j).getClassName_en():eggInstances.get(j).getClassName_ar() %></label>
      <span class="tooltiptext"><%=eggInstances.get(j).getDefinition().equals("none")?eggInstances.get(j).getComment():(eggInstances.get(j).getDefinition().equals("none")?"":eggInstances.get(j).getDefinition()) %></span>
      </li>
     
      <%} %>
    </ul>
  
  </div>
  <!-- ------------------------------------------------------------------------------- -->
  
</div>
<%} %>

</div>

<!-- ----------------------------------------- -->
        <br>
                <input class="toggle-box" id="identifier-8" type="checkbox">
<label for="identifier-8"><%=lang.equals("en")?"Cereals and grain products":"الحبوب ومنتجات الحبوب" %></label>

<div>
<%
ArrayList<ontologyClass> cerealArray=manager.getCereal(ontology,reasoner);
		for(int i=0;cerealArray.size()>i;i++){
%>
<button type="button" class="accordion"><%=lang.equals("en")?cerealArray.get(i).getClassName_en():cerealArray.get(i).getClassName_ar() %></button>
<div class="panel">
<!-- ------------------------------------------------------------------------------- -->
  <div class="container">
    <ul class="ks-cboxtags">
    <%
    ArrayList<ontologyClass> cerealInstances=cerealArray.get(i).getInstances();
    for(int j=0;cerealInstances.size()>j;j++){
    %>
      <li>
     
      <input type="checkbox" name="food" id=<%="cereal"+i+"_"+j %> value=<%=cerealInstances.get(j).getClassName_en().replace(" ", "_")+"$"+cerealInstances.get(j).getIriShortForm() %>>
      <label for=<%="cereal"+i+"_"+j %>><%=lang.equals("en")?cerealInstances.get(j).getClassName_en():cerealInstances.get(j).getClassName_ar() %></label>
      <span class="tooltiptext"><%=cerealInstances.get(j).getDefinition().equals("none")?cerealInstances.get(j).getComment():(cerealInstances.get(j).getDefinition().equals("none")?"":cerealInstances.get(j).getDefinition()) %></span>
      </li>
     
      <%} %>
    </ul>
  
  </div>
  <!-- ------------------------------------------------------------------------------- -->
  
</div>
<%} %>

</div>
<!-- ----------------------------------------- -->
        <br>
                <input class="toggle-box" id="identifier-9" type="checkbox">
<label for="identifier-9"><%=lang.equals("en")?"Bread":"المخبوزات" %></label>

<div>
<%
ArrayList<ontologyClass> breadArray=manager.getBread(ontology,reasoner);
		for(int i=0;breadArray.size()>i;i++){
%>
<button type="button" class="accordion"><%=lang.equals("en")?breadArray.get(i).getClassName_en():breadArray.get(i).getClassName_ar() %></button>
<div class="panel">
<!-- ------------------------------------------------------------------------------- -->
  <div class="container">
    <ul class="ks-cboxtags">
    <%
    ArrayList<ontologyClass> breadInstances=breadArray.get(i).getInstances();
    for(int j=0;breadInstances.size()>j;j++){
    %>
      <li>
      <input type="checkbox" name="food" id=<%="bread"+i+"_"+j %> value=<%=breadInstances.get(j).getClassName_en().replace(" ", "_")+"$"+breadInstances.get(j).getIriShortForm() %>>
      <label for=<%="bread"+i+"_"+j %>><%=lang.equals("en")?breadInstances.get(j).getClassName_en():breadInstances.get(j).getClassName_ar() %></label>
      <span class="tooltiptext"><%=breadInstances.get(j).getDefinition().equals("none")?breadInstances.get(j).getComment():(breadInstances.get(j).getDefinition().equals("none")?"":breadInstances.get(j).getDefinition()) %></span>
      </li>
     
      <%} %>
    </ul>
  
  </div>
  <!-- ------------------------------------------------------------------------------- -->
  
</div>
<%} %>

</div>
<!-- ----------------------------------------- -->
        <br>
                <input class="toggle-box" id="identifier-10" type="checkbox">
<label for="identifier-10"><%=lang.equals("en")?"Biscuits and crackers":"البسكويت و المقرمشات" %></label>

<div>
<%
ArrayList<ontologyClass> biscuitArray=manager.getBiscuitsAndCracker(ontology,reasoner);
		for(int i=0;biscuitArray.size()>i;i++){
%>
<button type="button" class="accordion"><%=lang.equals("en")?biscuitArray.get(i).getClassName_en():biscuitArray.get(i).getClassName_ar() %></button>
<div class="panel">
<!-- ------------------------------------------------------------------------------- -->
  <div class="container">
    <ul class="ks-cboxtags">
    <%
    ArrayList<ontologyClass> biscuitsInstances=biscuitArray.get(i).getInstances();
    for(int j=0;biscuitsInstances.size()>j;j++){
    %>
      <li>
      <input type="checkbox" name="food" id=<%="biscuits"+i+"_"+j %> value=<%=biscuitsInstances.get(j).getClassName_en().replace(" ", "_")+"$"+biscuitsInstances.get(j).getIriShortForm() %>>
      <label for=<%="biscuits"+i+"_"+j %>><%=lang.equals("en")?biscuitsInstances.get(j).getClassName_en():biscuitsInstances.get(j).getClassName_ar() %></label>
      <span class="tooltiptext"><%=biscuitsInstances.get(j).getDefinition().equals("none")?biscuitsInstances.get(j).getComment():(biscuitsInstances.get(j).getDefinition().equals("none")?"":biscuitsInstances.get(j).getDefinition()) %></span>
      </li>
     
      <%} %>
    </ul>
  
  </div>
  <!-- ------------------------------------------------------------------------------- -->
  
</div>
<%} %>

</div>
<!-- ----------------------------------------- -->
        <br>
<input class="toggle-box" id="identifier-11" type="checkbox">
<label for="identifier-11"><%=lang.equals("en")?"nonAlcoholic Beverages":"مشروبات غير كحولية" %></label>

<div>
<%
ArrayList<ontologyClass> nonAlcoholicArray=manager.getNonAlcoholicDrinks(ontology,reasoner);
		for(int i=0;nonAlcoholicArray.size()>i;i++){
%>
<button type="button" class="accordion"><%=lang.equals("en")?nonAlcoholicArray.get(i).getClassName_en():nonAlcoholicArray.get(i).getClassName_ar() %></button>
<div class="panel">
<!-- ------------------------------------------------------------------------------- -->
  <div class="container">
    <ul class="ks-cboxtags">
    <%
    ArrayList<ontologyClass> nonAlcoholicInstances=nonAlcoholicArray.get(i).getInstances();
    for(int j=0;nonAlcoholicInstances.size()>j;j++){
    %>
      <li>
      <input type="checkbox" name="food" id=<%="nonAlcoholicBeverage"+i+"_"+j %> value=<%=nonAlcoholicInstances.get(j).getClassName_en().replace(" ", "_")+"$"+nonAlcoholicInstances.get(j).getIriShortForm() %>>
      <label for=<%="nonAlcoholicBeverage"+i+"_"+j %>><%=lang.equals("en")?nonAlcoholicInstances.get(j).getClassName_en():nonAlcoholicInstances.get(j).getClassName_ar() %></label>
      <span class="tooltiptext"><%=nonAlcoholicInstances.get(j).getDefinition().equals("none")?nonAlcoholicInstances.get(j).getComment():(nonAlcoholicInstances.get(j).getDefinition().equals("none")?"":nonAlcoholicInstances.get(j).getDefinition()) %></span>
      </li>
     
      <%} %>
    </ul>
  
  </div>
  <!-- ------------------------------------------------------------------------------- -->
  
</div>
<%} %>

</div>
<!-- ----------------------------------------- -->
        <br>
                <input class="toggle-box" id="identifier-12" type="checkbox">
<label for="identifier-12"><%=lang.equals("en")?"Coffee drinks":"قهوة" %> </label>

<div>
<%
ArrayList<ontologyClass> coffeeArray=manager.getCoffeeDrinks(ontology,reasoner);
		for(int i=0;coffeeArray.size()>i;i++){
%>
<button type="button" class="accordion"><%=lang.equals("en")?coffeeArray.get(i).getClassName_en():coffeeArray.get(i).getClassName_ar() %></button>
<div class="panel">
<!-- ------------------------------------------------------------------------------- -->
  <div class="container">
    <ul class="ks-cboxtags">
    <%
    ArrayList<ontologyClass> coffeeInstances=coffeeArray.get(i).getInstances();
    for(int j=0;coffeeInstances.size()>j;j++){
    %>
      <li>
      <input type="checkbox" name="food" id=<%="coffee"+i+"_"+j %> value=<%=coffeeInstances.get(j).getClassName_en().replace(" ", "_")+"$"+coffeeInstances.get(j).getIriShortForm() %>>
      <label for=<%="coffee"+i+"_"+j %>><%=lang.equals("en")?coffeeInstances.get(j).getClassName_en():coffeeInstances.get(j).getClassName_ar() %></label>
      <span class="tooltiptext"><%=coffeeInstances.get(j).getDefinition().equals("none")?coffeeInstances.get(j).getComment():(coffeeInstances.get(j).getDefinition().equals("none")?"":coffeeInstances.get(j).getDefinition()) %></span>
      </li>
     
      <%} %>
    </ul>
  
  </div>
  <!-- ------------------------------------------------------------------------------- -->
  
</div>
<%} %>

</div>
<!-- ----------------------------------------- -->
        <br>
                <input class="toggle-box" id="identifier-13" type="checkbox">
<label for="identifier-13"><%=lang.equals("en")?"Alcoholic Beverages":"مشروبات كحولية" %> </label>

<div>
<%
ArrayList<ontologyClass> alcoholicArray=manager.getAlcoholicBeverages(ontology,reasoner);
		for(int i=0;alcoholicArray.size()>i;i++){
%>
<button type="button" class="accordion"><%=lang.equals("en")?alcoholicArray.get(i).getClassName_en():alcoholicArray.get(i).getClassName_ar() %></button>
<div class="panel">
<!-- ------------------------------------------------------------------------------- -->
  <div class="container">
    <ul class="ks-cboxtags">
    <%
    ArrayList<ontologyClass> alcoholicInstances=alcoholicArray.get(i).getInstances();
    for(int j=0;alcoholicInstances.size()>j;j++){
    %>
      <li>
      <input type="checkbox" name="food" id=<%="alcoholicBeverages"+i+"_"+j %> value=<%=alcoholicInstances.get(j).getClassName_en().replace(" ", "_")+"$"+alcoholicInstances.get(j).getIriShortForm() %>>
      <label for=<%="alcoholicBeverages"+i+"_"+j %>><%=lang.equals("en")?alcoholicInstances.get(j).getClassName_en():alcoholicInstances.get(j).getClassName_ar() %></label>
      <span class="tooltiptext"><%=alcoholicInstances.get(j).getDefinition().equals("none")?alcoholicInstances.get(j).getComment():(alcoholicInstances.get(j).getDefinition().equals("none")?"":alcoholicInstances.get(j).getDefinition()) %></span>
      </li>
     
      <%} %>
    </ul>
  
  </div>
  <!-- ------------------------------------------------------------------------------- -->
  
</div>
<%} %>

</div>



</div>
<button  type="button" class="collapsible">
<%=lang.equals("en")?"Which of these activities do you exert within the couples of hours before going to bed?":"أي من هذة الانشطة تقوم بها في الساعات الاخيرة قبل الذهاب الي النوم؟" %>

</button>

<div class="content">



<%
ArrayList<ontologyClass> activitiesArray=manager.getActivity(ontology,reasoner);
		for(int i=0;activitiesArray.size()>i;i++){
%>
<button type="button" class="accordion"><%=lang.equals("en")?activitiesArray.get(i).getClassName_en():activitiesArray.get(i).getClassName_ar() %></button>
<div class="panel">
<!-- ------------------------------------------------------------------------------- -->
  <div class="container">
    <ul class="ks-cboxtags">
    <%
    ArrayList<ontologyClass> activitiesInstances=activitiesArray.get(i).getInstances();
    for(int j=0;activitiesInstances.size()>j;j++){
    %>
      <li>
      <input type="checkbox" name="activity" id=<%="activities"+i+"_"+j %> value=<%=activitiesInstances.get(j).getClassName_en().replace(" ", "_")+"$"+activitiesInstances.get(j).getIriShortForm() %>>
      <label for=<%="activities"+i+"_"+j %>><%=lang.equals("en")?activitiesInstances.get(j).getClassName_en():activitiesInstances.get(j).getClassName_ar() %></label>
      <span class="tooltiptext"><%=activitiesInstances.get(j).getDefinition().equals("none")?activitiesInstances.get(j).getComment():(activitiesInstances.get(j).getDefinition().equals("none")?"":activitiesInstances.get(j).getDefinition()) %></span>
      </li>
     
      <%} %>
    </ul>
  
  </div>
  <!-- ------------------------------------------------------------------------------- -->
  
</div>
<%} %>

</div>


<button type="button" class="collapsible">
<%=lang.equals("en")?"How do you feel within the couple of hours before going to bed?":"كيف تشعر في الساعات الاخيرة قبل الذهاب من النوم؟" %>

</button>

<div class="content">




<button type="button" class="accordion"><%=lang.equals("en")?"Emotion Feelings":"مشاعر داخلية" %></button>
<div class="panel">
<!-- ------------------------------------------------------------------------------- -->
  <div class="container">
    <ul class="ks-cboxtags">
    <%
    ArrayList<ontologyClass> EmotionInstances=manager.getEmotionProcess(ontology,reasoner);
    for(int j=0;EmotionInstances.size()>j;j++){
    %>
      <li>
      <input type="checkbox" name="emotion" id=<%="emotionFeeling"+"_"+j %> value=<%=EmotionInstances.get(j).getClassName_en().replace(" ", "_")+"$"+EmotionInstances.get(j).getIriShortForm() %>>
      <label for=<%="emotionFeeling"+"_"+j %>><%=lang.equals("en")?EmotionInstances.get(j).getClassName_en():EmotionInstances.get(j).getClassName_ar() %></label>
      <span class="tooltiptext"><%=EmotionInstances.get(j).getDefinition().equals("none")?EmotionInstances.get(j).getComment():(EmotionInstances.get(j).getDefinition().equals("none")?"":EmotionInstances.get(j).getDefinition()) %></span>
      </li>
     
      <%} %>
    </ul>
  
  </div>
  <!-- ------------------------------------------------------------------------------- -->
  
</div>

<button type="button" class="accordion"><%=lang.equals("en")?"Bodily Feelings":"شعور داخل الجسم" %> </button>
<div class="panel">
<!-- ------------------------------------------------------------------------------- -->
  <div class="container">
    <ul class="ks-cboxtags">
    <%
    ArrayList<ontologyClass> EmotionBodilyInstances=manager.getBodilyProcess(ontology,reasoner);
    for(int j=0;EmotionBodilyInstances.size()>j;j++){
    %>
      <li>
      <input type="checkbox" name="emotion" id=<%="emotionBodilyFeeling"+"_"+j %> value=<%=EmotionBodilyInstances.get(j).getClassName_en().replace(" ","_")+"$"+EmotionBodilyInstances.get(j).getIriShortForm() %>>
      <label for=<%="emotionBodilyFeeling"+"_"+j %>><%=lang.equals("en")?EmotionBodilyInstances.get(j).getClassName_en():EmotionBodilyInstances.get(j).getClassName_ar() %></label>
      <span class="tooltiptext"><%=EmotionBodilyInstances.get(j).getDefinition().equals("none")?EmotionBodilyInstances.get(j).getComment():(EmotionBodilyInstances.get(j).getDefinition().equals("none")?"":EmotionBodilyInstances.get(j).getDefinition()) %></span>
      </li>
     
      <%} %>
    </ul>
  
  </div>
  <!-- ------------------------------------------------------------------------------- -->
  
</div>

</div>

<!-- ---------------------------------------------------------------------------------------------------------- -->
<button type="button" class="collapsible">
<%=lang.equals("en")?"Which of these behaviors do you do within the couple of hours before going to bed?":"اي من هذة التصرفات تقوم بها في الساعات الاخيرة قبل الذهاب الي النوم؟" %>
</button>

<div class="content">




<button type="button" class="accordion"><%=lang.equals("en")?"Consumption behavior":"تصرف استهلاكي" %></button>
<div class="panel">
<!-- ------------------------------------------------------------------------------- -->
  <div class="container">
    <ul class="ks-cboxtags">
    <%
    ArrayList<ontologyClass> consumptionInstances=manager.getConsumptionBehavior(ontology,reasoner);
    for(int j=0;consumptionInstances.size()>j;j++){
    %>
      <li>
      <input type="checkbox" name="behavior" id=<%="consumption"+"_"+j %> value=<%=consumptionInstances.get(j).getClassName_en().replace(" ", "_")+"$"+consumptionInstances.get(j).getIriShortForm() %>>
      <label for=<%="consumption"+"_"+j %>><%=lang.equals("en")?consumptionInstances.get(j).getClassName_en():consumptionInstances.get(j).getClassName_ar() %></label>
      <span class="tooltiptext"><%=consumptionInstances.get(j).getDefinition().equals("none")?consumptionInstances.get(j).getComment():(consumptionInstances.get(j).getDefinition().equals("none")?"":consumptionInstances.get(j).getDefinition()) %></span>
      </li>
     
      <%} %>
    </ul>
  
  </div>
  <!-- ------------------------------------------------------------------------------- -->
  
</div>

<button type="button" class="accordion"><%=lang.equals("en")?"Entertainment behaviors":"وسائل الترفيه" %></button>
<div class="panel">
<!-- ------------------------------------------------------------------------------- -->
  <div class="container">
    <ul class="ks-cboxtags">
    <%
    ArrayList<ontologyClass> entertainmentInstances=manager.getEntertainmentBehavior(ontology,reasoner);
    for(int j=0;entertainmentInstances.size()>j;j++){
    %>
      <li>
      <input type="checkbox" name="behavior" id=<%="entertainment"+"_"+j %> value=<%=entertainmentInstances.get(j).getClassName_en().replace(" ", "_")+"$"+entertainmentInstances.get(j).getIriShortForm() %>>
      <label for=<%="entertainment"+"_"+j %>><%=lang.equals("en")?entertainmentInstances.get(j).getClassName_en():entertainmentInstances.get(j).getClassName_ar() %></label>
      <span class="tooltiptext"><%=entertainmentInstances.get(j).getDefinition().equals("none")?entertainmentInstances.get(j).getComment():(entertainmentInstances.get(j).getDefinition().equals("none")?"":entertainmentInstances.get(j).getDefinition()) %></span>
      </li>
     
      <%} %>
    </ul>
  
  </div>
  <!-- ------------------------------------------------------------------------------- -->
  
</div>


<button type="button" class="accordion"><%=lang.equals("en")?"Sleep behaviors":"تصرفات متعلقة بالنوم" %></button>
<div class="panel">
<!-- ------------------------------------------------------------------------------- -->
  <div class="container">
    <ul class="ks-cboxtags">
    <%
    ArrayList<ontologyClass> sleepInstances=manager.getSleepBehavior(ontology,reasoner);
    for(int j=0;sleepInstances.size()>j;j++){
    %>
      <li>
      <input type="checkbox" name="behavior" id=<%="sleepBehavior"+"_"+j %> value=<%=sleepInstances.get(j).getClassName_en().replace(" ", "_")+"$"+sleepInstances.get(j).getIriShortForm() %>>
      <label for=<%="sleepBehavior"+"_"+j %>><%=lang.equals("en")?sleepInstances.get(j).getClassName_en():sleepInstances.get(j).getClassName_ar() %></label>
      <span class="tooltiptext"><%=sleepInstances.get(j).getDefinition().equals("none")?sleepInstances.get(j).getComment():(sleepInstances.get(j).getDefinition().equals("none")?"":sleepInstances.get(j).getDefinition()) %></span>
      </li>
     
      <%} %>
    </ul>
  
  </div>
  <!-- ------------------------------------------------------------------------------- -->
  
</div>



</div>


<!-- ----------------------------shellehaa-------------------------------------------------------- -->



<button type="button" class="collapsible">
<%=lang.equals("en")?"Do you suffer from any of these issues?":"هل تعاني من احدي هذه المشكلات؟" %>

</button>

<div class="content">






<button type="button" class="accordion"><%=lang.equals("en")?"Sleep disorders":"مشكلات النوم" %> </button>
<div class="panel">
<!-- ------------------------------------------------------------------------------- -->
  <div class="container">
    <ul class="ks-cboxtags">
    <%
    ArrayList<ontologyClass> sleepDisorder=manager.getSleepDisorder(ontology,reasoner);
    for(int j=0;sleepDisorder.size()>j;j++){
    %>
      <li>
      <input type="checkbox" name="sleepDisorder" id=<%="sleepDisorder"+"_"+j %> value=<%=sleepDisorder.get(j).getClassName_en().replace(" ","_")+"$"+sleepDisorder.get(j).getIriShortForm() %>>
      <label for=<%="sleepDisorder"+"_"+j %>><%=lang.equals("en")?sleepDisorder.get(j).getClassName_en():sleepDisorder.get(j).getClassName_ar() %></label>
      <span class="tooltiptext"><%=sleepDisorder.get(j).getComment().equals("none")?sleepDisorder.get(j).getDefinition():sleepDisorder.get(j).getComment() %></span>
      </li>
     
      <%}
    
    
    
    
    
	
    
    
    %>
    </ul>
  
  </div>
  <!-- ------------------------------------------------------------------------------- -->
  
</div>

</div>






<!-- ----------------------------------------------------------------------------------------------- -->




    </div>
<br>
<hr>
<br>
<div>

 <h1 style="font-family: monospace;"><%=lang.equals("en")?"Feedback":"رأيك" %> </h1>
  
  <div class="container" >
  <h2 style="font-family: monospace;"><%=lang.equals("en")?"how do you feel about the choices":"كيف تشعر تجاه هذه الاختيارات؟" %></h2>
    <ul class="ks-cboxtags">
      <li><input name="opinion" type="checkbox" id="checkboxOne" value="it was expressive and good"><label for="checkboxOne"><%=lang.equals("en")?"it was expressive and good":"لقد كانت الاختيارات معبرة وجيدة" %></label></li>
      <li><input name="opinion" type="checkbox" id="checkboxTwo" value="i didnot understand alot of stuff" ><label for="checkboxTwo"><%=lang.equals("en")?"i didnot understand alot of stuff":"لم استطع علي استيعاب اختيارات عديدة" %></label></li>
      <li><input name="opinion" type="checkbox" id="checkboxThree" value="it was long and horrible" ><label for="checkboxThree"><%=lang.equals("en")?"it was long and horrible":"لقد كان طويل و متعب" %></label></li>
      <li><input name="opinion" type="checkbox" id="checkboxFour" value="it was long but i managed to express using these tags" ><label for="checkboxFour"><%=lang.equals("en")?"it was long but i managed to express using these tags":"لقد كان طويل و لكن تمكنت من التعبير باستخدام هذه الاختيارات" %></label></li>
    
    </ul>
  
  </div>
       <label for="message"><%=lang.equals("en")?"Suggestions":"اقتراحات" %></label>
   
    <textarea name="suggestion" id="message" cols="30" rows="10" placeholder="Message"></textarea>
 

</div>    
   
    <input name="submit" type="submit" value="Submit">
    
  </form>
  </div>
  </div>
<script>
var coll = document.getElementsByClassName("collapsible");
var i;

for (i = 0; i < coll.length; i++) {
  coll[i].addEventListener("click", function() {
    this.classList.toggle("active");
    var content = this.nextElementSibling;
    if (content.style.display === "block") {
      content.style.display = "none";
    } else {
      content.style.display = "block";
    }
  });
}
var acc = document.getElementsByClassName("accordion");
var i;

for (i = 0; i < acc.length; i++) {
  acc[i].addEventListener("click", function() {
    this.classList.toggle("active");
    var panel = this.nextElementSibling;
    if (panel.style.display === "block") {
      panel.style.display = "none";
    } else {
      panel.style.display = "block";
    }
  });
}

document.onreadystatechange = function() { 
    if (document.readyState !== "complete") { 
        document.querySelector( 
          "body").style.visibility = "hidden"; 
        document.querySelector( 
          "#loader").style.visibility = "visible"; 
    } else { 
        document.querySelector( 
          "#loader").style.display = "none"; 
        document.querySelector( 
          "body").style.visibility = "visible"; 
    } 
};
</script>

</body>

</html>