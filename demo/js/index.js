 $(document).ready(function() {
   var loadedUrl = $(location).attr('href');
   var lastIndex = loadedUrl.lastIndexOf("#");

   var targetPage;
   if (lastIndex != -1)
     targetPage = loadedUrl.substring(lastIndex + 1);
   else
     targetPage = "home";

   loadPage(targetPage);
 });

 $(".master_nav").click(function() {
   var navbarState = $(".navbar-toggle").is(":visible");
   if (navbarState)
     $('.navbar-toggle').click();

   var targetName = $(this).attr('id');
   //remove "a_nav_" from clicked navigation link
   targetName = targetName.substring(6);

   loadPage(targetName);
 });

 function loadPage(pageName) {
   //create a navigation url from the id of the clicked element
   var targetUrl = "./" + pageName + ".html";

   //load the content from the specified html from into master div content
   $.get(targetUrl, function(data) {
     $("#div_master_content").html(data);

     var currentUrl = window.location.host + "master.html";
     var displayUrl = currentUrl + "#" + pageName;
     window.history.pushState(pageName, pageName, displayUrl);
   });
 }
