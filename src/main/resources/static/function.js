function deleteCat(id){
	swal({
  title: "Are you sure?",
  text: "Once deleted, you will not be able to recover this record!",
  icon: "warning",
  buttons: true,
  dangerMode: true,
})
.then((OK) => {
	if (OK) {
		$.ajax({
			url:"/deleteCaregorie/"+id,
			success:function(res){
				console.log(res);
				console.log(id);
			},
		});
		
		  swal("Good! Your record has been deleted!", {
      icon: "success",
	}).then((OK)=>{
		if(OK){
			location.href="/CategorieForm";
		}
	});
	} else {
    swal("Your record is safe!");
  }
});
}