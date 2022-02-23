$("document").ready(function() {
  $(".custom-file-input").on("change", function(event) {
    let fileName = event.target.files[0].name;
    $(".custom-file-label").html(fileName);
  });
});