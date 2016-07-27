$(document).ready(function() {
    $.ajax({
        url: "http://127.0.0.1:7379/get/all_points"
    }).then(function(data) {
       /*
       $('.webdis-respons').append(data.get);

       $('.webdis-userid').append("test");
       $('.webdis-lat').append(jQuery.parseJSON(data.get).lat);
       $('.webdis-lon').append(jQuery.parseJSON(data.get).lon);
       $('.webdis-date').append(jQuery.parseJSON(data.get).date);
       $('.webdis-time').append(jQuery.parseJSON(data.get).time);
		*/
       var trHTML = '';
       var points = jQuery.parseJSON(data.get);

	   $.each(points, function (i, item) {
          	trHTML += '<tr><td>' + item.user_id + '</td><td>' + item.lat + '</td><td>' + item.lng + '</td><td>' + item.date + '</td><td>' + item.time + '</td></tr>';
       });
        
       //$('#locations').append(trHTML);	
    });
})

var map;

function getData() {
  $.ajax({
      url: "http://127.0.0.1:7379/get/all_geopoints",
  }).success(function(data){
      var points = jQuery.parseJSON(data.get);
      console.log(points);
      map.data.addGeoJson(points);
  })  
  setTimeout("getData()", 5000);

}

function initMap() {
	map = new google.maps.Map(document.getElementById('map'), {
	  center: {lat: 40.009328, lng: 116.320887},
	  zoom: 13
	});

	var marker = new google.maps.Marker({
	  position: {"lat": 40.009328, "lng": 116.320887},
	  title: "User_id: 128"
	});
	//marker.setMap(map);
  getData();

}




