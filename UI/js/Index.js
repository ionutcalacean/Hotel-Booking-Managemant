
$(document).ready(function()    {
	
	var username_var;
	var password_var;
	
	var username_create;
	var password_create;
	var city_create;
	var phone_create;
	var email_create;
	
	var username_update;
	var password_update;
	var city_update;
	var phone_update;
	var email_update;
	
	var hotel_id= [];
	var hotel_name = [];
	var hotel_city = [];
	var hotel_street = [];
	var hotel_phone = [];
	
	var room_id= [];
	var room_price = [];
	var room_floor = [];
	var room_number = [];
	var room_capacity = [];
	var room_free = [];
	
	
	
	function myFunction(){  
           
		$.ajax({
            type: 'GET',
            url: 'http://localhost:8080/admins/login?username='+username_var+'&password='+password_var,
            dataType: 'json',
            success: function(data){
				
				$("#login_message").text("Welcome, Admin "+data.username +"!");
				$('#admin_button').show();
            },
            error: function(){
               	$("#login_message").text("Invalid username or password! Try Again!");
            }

        });		   
    }

	

	
	$("#admin_button").click(function() {
		
	
		
		window.location.href = "admin.html";
		
	});
	
  /* $("#request_temp").click(function(e)    {
		
		
		
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/users/login?username=ioana&password=password',
            dataType: 'json',
            success: function(data){
				$('#users').append('<li>'+username_var+' </li>');
            },
            error: function(){
                $("#temp").html("error")
            }

        });
    });*/
	
	$("#login_button").click(function() {
		
		//USEFULL TIPS
		//$("#login_button").hide();
		//	window.location.href = "apply.html";
		
		
	    username_var = $("#username").val();
		password_var =  $("#password").val();
		
		$.ajax({
            type: 'GET',
            url: 'http://localhost:8080/users/login?username='+username_var+'&password='+password_var,
            dataType: 'json',
            success: function(data){
				$("#login_message").text("Welcome, User "+data.username +"!");
				$("#user_div").show();
				
				    localStorage.setItem("username", data.username);
					localStorage.setItem("city", data.city);
					localStorage.setItem("phone",data.phone);
					localStorage.setItem("email", data.email);
					localStorage.setItem("password", data.password);
					
            },
            error: function(){
			
				myFunction();
			
              
            }

        });
		
	});
	
		$("#register_button").click(function() {
		
		//$("#login_button").hide();
		
	    username_create = $("#username_create").val();
		password_create =  $("#password_create").val();
		phone_create =  $("#phone_create").val();
		email_create =  $("#email_create").val();
		city_create =  $("#city_create").val();
		
		$.ajax({
            type: 'POST',
            url: 'http://localhost:8080/users/create?username='+username_create+'&password='+password_create+'&phone='+phone_create+'&email='+email_create+'&city='+city_create+'&news='+' ',
            dataType: 'json',
            success: function(data){
				$("#register_message").text("User "+ username_create +" created successful!");
				
            },
            error: function(){
				
				$("#register_message").text("Failed!");
              
            }

        });
		
	});
	
		$("#findall_button").click(function() {
		
	
		
		$.ajax({
            type: 'GET',
            url: 'http://localhost:8080/users/getall',
            dataType: 'json',
            success: function(data){
				$.each(data,function(i,user) {
					
					$('#users').append('<li>User '+ i+ '-> Username: ' +user.username + ', password: ' +user.password+ ', city: '+ user.city + ',email : '+ user.email+',news for user:'+ user.news + '; </li>' );
					
				});
				
            },
            error: function(){
			
				$('#users').append('<li>Error !</li>' );
              
            }

        });
		
	});

	$("#update_user_button").click(function() {
		
		username_update = $("#username_update").val();
		password_update =  $("#password_update").val();
		phone_update =  $("#phone_update").val();
		email_update =  $("#email_update").val();
		city_update =  $("#city_update").val();
		
		$.ajax({
            type: 'PUT',
            url: 'http://localhost:8080/users/update/'+username_update,
			data: JSON.stringify({
									username:username_update,
									password:password_update,
									city: city_update,
									email:email_update,
									phone:phone_update,
									news: " "
								}),
			contentType: "application/json; charset=utf-8",
            dataType: 'json',
            success: function(data){
			
					$('#user_updated').append('<li>User '+ '-> Username: '+ username_update+ ', password: ' +password_update + ', city: ' +city_update+ ', email: ' +email_update+ ', phone: ' + phone_update+ '; </li>' );
					
				
				
            },
            error: function(){
			
				$('#user_updated').append('<li>Error !</li>' );
              
            }
    

        });
		
	});
	
	$("#delete_user_button").click(function() {
		
		var username_delete = $("#username_delete").val();

		
		$.ajax({
            type: 'DELETE',
            url: 'http://localhost:8080/users/deletebyid/'+username_delete,
            dataType: 'json',
            success: function(data){
			
					$('#user_deleted').append('<li>User '+username_delete+ '  deleted successful! </li>' );
					
				
				
            },
            error: function(){
			
				$('#user_deleted').append('<li>Error !</li>' );
              
            }
    

        });
		
	});
	
	$("#delete_all_users").click(function() {
		

		
		$.ajax({
            type: 'DELETE',
            url: 'http://localhost:8080/users/deleteall',
            dataType: 'json',
            success: function(data){
			
					$('#deleted_all_message').append('<li> All users deleted! </li>' );
					
				
				
            },
            error: function(){
			
				$('#deleted_all_message').append('<li>Error !</li>' );
              
            }
    

        });
		
	});
	
	$("#find_user_button").click(function() {
		
		var username_details = $("#username_details").val();

		
		$.ajax({
            type: 'GET',
            url: 'http://localhost:8080/users/findByUsername?username='+username_details,
            dataType: 'json',
            success: function(data){
			
					$('#user_details').append('<li>Username: '+data.username + ', Password :'+data.password+ ', City: ' + data.city + ', Phone: '+ data.phone + ', Email: '+data.email +' </li>' );
					
				
				
            },
            error: function(){
			
				$('#user_details').append('<li>Error !</li>' );
              
            }
    

        });
		
	});
	
	$("#city_statistic_button").click(function() {
		
		var city_name = $("#city_name").val();

		
		$.ajax({
            type: 'GET',
            url: 'http://localhost:8080/users/findByCity?city='+city_name,
            dataType: 'json',
            success: function(data){
				if(data.length == 0) $('#city_statistic').append('<li>Users not found !</li>' );
				
				$.each(data,function(i,user) {
					
					$('#city_statistic').append('<li>User '+ i+ '-> Username: ' +user.username + ', password: ' +user.password+ ', city: '+ user.city +', phone: '+ user.phone + ', email'+ user.email +'; </li>' );
					
				});
			
					
				
				
            },
            error: function(){
			
				$('#city_statistic').append('<li>Error !</li>' );
              
            }
    

        });
		
	});
	
	$("#findall_admin_button").click(function() {
		
	
		
		$.ajax({
            type: 'GET',
            url: 'http://localhost:8080/admins/getall',
            dataType: 'json',
            success: function(data){
				$.each(data,function(i,admin) {
					
					$('#admins').append('<li>Admin '+ i+ '-> Username: ' +admin.username + ', password: ' +admin.password+ '; </li>' );
					
				});
				
            },
            error: function(){
			
				$('#admins').append('<li>Error !</li>' );
              
            }

        });
		
	});
	
		
	$("#create_admin_button").click(function() {
		
		var username_admin = $("#username_admin").val();
		var password_admin =  $("#password_admin").val();
		
		$.ajax({
            type: 'POST',
            url: 'http://localhost:8080/admins/create?username='+username_admin+'&password='+password_admin,
            dataType: 'json',
            success: function(data){
				
					
					$('#admin_create ').append('<li>Admin '+  '-> Username: ' +username_admin + ', password: ' +password_admin+ ' created successful! </li>' );
					
				
				
            },
            error: function(){
			
				$('#admin_create').append('<li>Error !</li>' );
              
            }

        });
		
	});
	
		$("#update_admin_button").click(function() {
		
		username_admin_update = $("#username_admin_update").val();
		password_admin_update =  $("#password_admin_update").val();

		
		$.ajax({
            type: 'PUT',
            url: 'http://localhost:8080/admins/update/'+username_admin_update,
			data: JSON.stringify({
									username:username_admin_update,
									password:password_admin_update
						
								}),
			contentType: "application/json; charset=utf-8",
            dataType: 'json',
            success: function(data){
			
					$('#admin_updated').append('<li>Admin '+ '-> Username: '+ username_admin_update+ ', password: ' +password_admin_update + '-> updated or inserted if not found! </li>' );
					
				
				
            },
            error: function(){
			
				$('#admin_updated').append('<li>Error !</li>' );
              
            }
    

        });
		
	});
	
	$("#delete_admin_button").click(function() {
		
		var username_admin_delete = $("#username_admin_delete").val();

		
		$.ajax({
            type: 'DELETE',
            url: 'http://localhost:8080/admins/deletebyid/'+username_admin_delete,
            dataType: 'json',
            success: function(data){
			
					$('#admin_deleted').append('<li>Admin '+username_admin_delete+ '  deleted successful! </li>' );
					
				
				
            },
            error: function(){
			
				$('#admin_deleted').append('<li>Error !</li>' );
              
            }
    

        });
		
	});
	
	$("#delete_all_admins").click(function() {
		

		
		$.ajax({
            type: 'DELETE',
            url: 'http://localhost:8080/admins/deleteall',
            dataType: 'json',
            success: function(data){
			
					$('#deleted_all_admins_message').append('<li> All admins deleted! </li>' );
					
				
				
            },
            error: function(){
			
				$('#deleted_all_admins_message').append('<li>Error !</li>' );
              
            }
    

        });
		
	});
	
	$("#find_admin_button").click(function() {
		
		var username_admin_details = $("#username_admin_details").val();

		
		$.ajax({
            type: 'GET',
            url: 'http://localhost:8080/admins/findByUsername?username='+username_admin_details,
            dataType: 'json',
            success: function(data){
			
					$('#admin_details').append('<li>Username: '+data.username + ', Password :'+data.password+ ' </li>' );
					
				
				
            },
            error: function(){
			
				$('#admin_details').append('<li>Error !</li>' );
              
            }
    

        });
		
	});
	
		$("#findall_hotel_button").click(function() {
		
	
		
		$.ajax({
            type: 'GET',
            url: 'http://localhost:8080/hotel/getall',
            dataType: 'json',
            success: function(data){
				$.each(data,function(i,hotel) {
					
					$('#hotels').append('<li>' + i+'->Hotel ID:'+ hotel.hotelId+  '-> Hotel name: ' +hotel.hotelName + ', city: ' +hotel.city+ ', street: '+hotel.street + ', phone: '+hotel.phone+'; </li>' );
						
						hotel_id[i]= hotel.hotelId;
						hotel_name[i] = hotel.hotelName;
						hotel_city[i] = hotel.city;
					    hotel_street[i] = hotel.street;
						hotel_phone[i] = hotel.phone;
					
				});
				
            },
            error: function(){
			
				$('#hotels').append('<li>Error !</li>' );
              
            }

        });
		
	});
	

	$("#create_hotel_button").click(function() {
		
	
		
	    var hotelname_create = $("#hotelname_create").val();
		var hotelcity_create =  $("#hotelcity_create").val();
		var hotelstreet_create =  $("#hotelstreet_create").val();
		var hotelphone_create =  $("#hotelphone_create").val();
		
		
		$.ajax({
            type: 'POST',
            url: 'http://localhost:8080/hotel/create',
			data: JSON.stringify({
									hotelName:hotelname_create,
									street:hotelstreet_create,
									city:hotelcity_create,
									phone:hotelphone_create
						
								}),
			contentType: "application/json; charset=utf-8",
            dataType: 'json',
            success: function(data){
				$("#created_hotel").text("Hotel with name : "+ hotelname_create +', city: '+hotelcity_create + ', street: '+hotelstreet_create +', phone :' +hotelphone_create + "; created successful!");
				
            },
            error: function(){
				
				$("#created_hotel").text("Failed!");
              
            }

        });
		
	});
	
		$("#update_hotel_button").click(function() {
		
	
		var hotel_id= $('#hotel_id').val();
	    var hotelname_update = $("#hotelname_update").val();
		var hotelcity_update =  $("#hotelcity_update").val();
		var hotelstreet_update =  $("#hotelstreet_update").val();
		var hotelphone_update =  $("#hotelphone_update").val();
		
		
		$.ajax({
            type: 'PUT',
            url: 'http://localhost:8080/hotel/update/'+hotel_id,
			data: JSON.stringify({
									hotelName:hotelname_update,
									street:hotelstreet_update,
									city:hotelcity_update,
									phone:hotelphone_update
						
								}),
			contentType: "application/json; charset=utf-8",
            dataType: 'json',
            success: function(data){
				$("#updated_hotel").text(" Hotel new name : "+ hotelname_update +', city: '+hotelcity_update + ', street: '+hotelstreet_update +', phone :' +hotelphone_update + "; updated successful!");
				
            },
            error: function(){
				
				$("#updated_hotel").text("Failed!");
              
            }

        });
		
	});
	
		$("#delete_id_button").click(function() {
		
		var id_hotel_delete = $("#id_hotel_delete").val();

		
		$.ajax({
            type: 'DELETE',
            url: 'http://localhost:8080/hotel/deletebyid/'+id_hotel_delete,
            dataType: 'json',
            success: function(data){
			
					$('#hotel_deleted').append('<li>Hotel with id:  '+id_hotel_delete+ '  deleted successful! </li>' );
					
				
				
            },
            error: function(){
			
				$('#hotel_deleted').append('<li>Error !</li>' );
              
            }
    

        });
		
	});
	
	$("#delete_all_hotels").click(function() {
		

		
		$.ajax({
            type: 'DELETE',
            url: 'http://localhost:8080/hotel/deleteall',
            dataType: 'json',
            success: function(data){
			
					$('#deleted_all_hotels_message').append('<li> All hotels deleted! </li>' );
					
				
				
            },
            error: function(){
			
				$('#deleted_all_hotels_message').append('<li>Error !</li>' );
              
            }
    

        });
		
	});
	
	$("#findall_rooms_button").click(function() {
		
	
		
		$.ajax({
            type: 'GET',
            url: 'http://localhost:8080/rooms/getall',
            dataType: 'json',
            success: function(data){
				$.each(data,function(i,room) {
					
					$('#rooms').append('<li>Room '+ i+ '-> Id : ' +room.roomId + ', price per night: ' +room.pricePerNight +   ', floor : '+room.floor + ', room Number: ' + room.roomNb + ', capacity :'+ room.capacity+ ', FREE :'+ 
								room.free+ ', Hotel id:'+ room.hotel.hotelId + ', Hotel name: '+ room.hotel.hotelName+ ', city: '+ room.hotel.city + '; </li>' );
					
				});
				
            },
            error: function(){
			
				$('#rooms').append('<li>Error !</li>' );
              
            }

        });
		
	});
	
		 
		 
	function findHotels_function(){  
           
			$.ajax({
            type: 'GET',
            url: 'http://localhost:8080/hotel/getall',
            dataType: 'json',
            success: function(data){
				$.each(data,function(i,hotel) {
					
						
						hotel_id[i]= hotel.hotelId;
						hotel_name[i] = hotel.hotelName;
						hotel_city[i] = hotel.city;
					    hotel_street[i] = hotel.street;
						hotel_phone[i] = hotel.phone;
					
				});
				
            },
            error: function(){
			
				//$('#hotels').append('<li>Error !</li>' );
              
            }

        });
    }
	
		 
		var roomtype ;
		var hotel_id_room ;
		var price_room ;
		var floor_room ;
		var number_room ;
		var capacity_room;
		var free_room;
		

		var index;
	var reg;
	var pre ;
	var conf;
	var url_string ;
		
	
		$("#create_room_button").click(function() {
		
	
		
	 
		roomtype =$("#roomtype").val();
		hotel_id_room = $("#hotel_id_room").val();
	    price_room =  $("#price_room").val();
	    floor_room =  $("#floor_room").val();
	    number_room =  $("#number_room").val();
	    capacity_room =  $("#capacity_room").val();
	    free_room =  $("#free_room").val();
		
		findHotels_function();
		

	 reg = "RegularRoom";
	 pre = "PremiumRoom";
	 conf= "ConferenceRoom"
	 url_string = 'http://localhost:8080/rooms/create/'+roomtype;
		
	if(reg.localeCompare(roomtype) == 0 )
	{
		$('#matrimonialbad_label').show();
		$('#matrimonialbad').show();
		
	}
	if(pre.localeCompare(roomtype) == 0 )
	{
		$('#balcony_label').show();
		$('#balcony').show();
		$('#roomservice_label').show();
		$('#roomservice').show();
		$('#spa_label').show();
		$('#spa').show();
		
	}
	if(conf.localeCompare(roomtype) == 0 )
	{
		$('#projector_label').show();
		$('#projector').show();
		$('#leatherseats_label').show();
		$('#leatherseats').show();
		
	}
		
	});
	
	$("#create_room_button_final").click(function() {
		
	
		var matrimonialbad;
		var balcony;
		var roomservice;
		var spa;
		var projector;
		var leatherseats;
		
		index=0;
		for (var i=0; i < hotel_id.length; i++) {             
				if(hotel_id[i] == hotel_id_room)
				{
					index = i;
				}
  
		}
	
	if(pre.localeCompare(roomtype) == 0 )
	{
		balcony = $('#balcony').val();
		roomservice = $('#roomservice').val();
		spa = $('#spa').val();
		url_string += '/'+balcony + '/' + roomservice+ '/'+ spa;
		
	}
	else {url_string += '/'+"false" + '/' + "false"+ '/'+ "false";}

	if(conf.localeCompare(roomtype) == 0 )
	{
		projector = $('#projector').val();
		leatherseats = $('#leatherseats').val();
		url_string += '/'+projector + '/' + leatherseats;
		
	}
	else {url_string += '/'+"false" + '/' + "false";}
	if(reg.localeCompare(roomtype) == 0 )
	{
		matrimonialbad = $('#matrimonialbad').val();
		
		url_string += '/'+matrimonialbad;
		
	}
	else {url_string += '/'+"false";}
	

		$.ajax({
            type: 'POST',
            url: url_string,
			data: JSON.stringify({
									pricePerNight:price_room,
									floor:floor_room,
									roomNb:number_room,
									capacity:capacity_room,
									free:free_room,
									hotel:
									{
										hotelId:hotel_id[index],
										city:hotel_city[index],
										hotel_name:hotel_name[index],
										phone:hotel_phone[index],
										street:hotel_street[index]

									}
						
								}),
			contentType: "application/json; charset=utf-8",
            dataType: 'json',
            success: function(data){
				$("#room_created").append("<li>Room with number : "+ number_room +', price per night: : '+price_room + ', floor: : '+floor_room +', capacity :' +capacity_room + "; created successful at hotel: </li>");
				$("#room_created").append("<li>Hotel with " +hotel_id[index] + ", city :" + hotel_city[index]+ ", street : "+ hotel_street[index] + ",name :" + hotel_name[index]+"; </li>")
				
            },
            error: function(){
				
				$("#room_created").text("Failed!");
              
            }

        });
		
	});
	
	$("#update_room_button").click(function() {
		
	
		roomtype =$("#roomtype_update").val();
		roomid =$("#roomid").val();
		hotel_id_room = $("#hotel_id_room_update").val();
	    price_room =  $("#price_room_update").val();
	    floor_room =  $("#floor_room_update").val();
	    number_room =  $("#number_room_update").val();
	    capacity_room =  $("#capacity_room_update").val();
	    free_room =  $("#free_room_update").val();
		
		findHotels_function();

	 reg = "RegularRoom";
	 pre = "PremiumRoom";
	 conf= "ConferenceRoom"
	 url_string = 'http://localhost:8080/rooms/update/'+roomid;
		
	if(reg.localeCompare(roomtype) == 0 )
	{
		$('#matrimonialbad_label_update').show();
		$('#matrimonialbad_update').show();
		
	}
	if(pre.localeCompare(roomtype) == 0 )
	{
		$('#balcony_label_update').show();
		$('#balcony_update').show();
		$('#roomservice_label_update').show();
		$('#roomservice_update').show();
		$('#spa_label_update').show();
		$('#spa_update').show();
		
	}
	if(conf.localeCompare(roomtype) == 0 )
	{
		$('#projector_label_update').show();
		$('#projector_update').show();
		$('#leatherseats_label_update').show();
		$('#leatherseats_update').show();
		
	}
		
	});
	
	$("#update_room_button_final").click(function() {
		
	
		var matrimonialbad;
		var balcony;
		var roomservice;
		var spa;
		var projector;
		var leatherseats;
		
		index=0;
		for (var i=0; i < hotel_id.length; i++) {             
				if(hotel_id[i] == hotel_id_room)
				{
					index = i;
				}
  
		}
	
	if(pre.localeCompare(roomtype) == 0 )
	{
		balcony = $('#balcony_update').val();
		roomservice = $('#roomservice_update').val();
		spa = $('#spa_update').val();
		url_string += '/'+balcony + '/' + roomservice+ '/'+ spa;
		
	}
	else {url_string += '/'+"false" + '/' + "false"+ '/'+ "false";}

	if(conf.localeCompare(roomtype) == 0 )
	{
		projector = $('#projector_update').val();
		leatherseats = $('#leatherseats_update').val();
		url_string += '/'+projector + '/' + leatherseats;
		
	}
	else {url_string += '/'+"false" + '/' + "false";}
	if(reg.localeCompare(roomtype) == 0 )
	{
		matrimonialbad = $('#matrimonialbad_update').val();
		
		url_string += '/'+matrimonialbad;
		
	}
	else {url_string += '/'+"false";}
	

		$.ajax({
            type: 'PUT',
            url: url_string,
			data: JSON.stringify({
									pricePerNight:price_room,
									floor:floor_room,
									roomNb:number_room,
									capacity:capacity_room,
									free:free_room,
									hotel:
									{
										hotelId:hotel_id[index],
										city:hotel_city[index],
										hotel_name:hotel_name[index],
										phone:hotel_phone[index],
										street:hotel_street[index]

									}
						
								}),
			contentType: "application/json; charset=utf-8",
            dataType: 'json',
            success: function(data){
				$("#room_updated").append("<li>Room with number : "+ number_room +', price per night: : '+price_room + ', floor: : '+floor_room +', capacity :' +capacity_room + "; created successful at hotel: </li>");
				$("#room_updated").append("<li>Hotel with " +hotel_id[index] + ", city :" + hotel_city[index]+ ", street : "+ hotel_street[index] + ",name :" + hotel_name[index]+"; </li>")
				
            },
            error: function(){
				
				$("#room_updated").text("Failed!");
              
            }

        });
		
	});
	
	
	$("#delete_id_room_button").click(function() {
		
		var id_room_delete = $("#id_room_delete").val();

		
		$.ajax({
            type: 'DELETE',
            url: 'http://localhost:8080/rooms/deletebyid/'+id_room_delete,
            dataType: 'json',
            success: function(data){
			
					$('#room_deleted').append('<li>Room with id:  '+id_room_delete+ '  deleted successful! </li>' );
					
				
				
            },
            error: function(){
			
				$('#room_deleted').append('<li>Error !</li>' );
              
            }
    

        });
		
	});
	
		$("#delete_all_rooms").click(function() {
		

		
		$.ajax({
            type: 'DELETE',
            url: 'http://localhost:8080/rooms/deleteall',
            dataType: 'json',
            success: function(data){
			
					$('#deleted_all_rooms_message').append('<li> All rooms deleted! </li>' );
					
				
				
            },
            error: function(){
			
				$('#deleted_all_rooms_message').append('<li>Error !</li>' );
              
            }
    

        });
		
	});
	
	$("#notify_users").click(function() {
		

		
		$.ajax({
            type: 'GET',
            url: 'http://localhost:8080/rooms/check',
            dataType: 'json',
            success: function(data){
			
					//$('#news').append('<li> All users notified! </li>' );
					
				
				
            },
            error: function(){
			
				//$('#news').append('<li>Error !</li>' );
              
            }
    

        });
		$('#news').append('<li> All users notified! </li>' );
		
	});
	
	$("#show_user_details").click(function() {
		
	
		var username_local = localStorage.getItem("username");
		
		$.ajax({
            type: 'GET',
            url: 'http://localhost:8080/users/findByUsername?username='+username_local,
            dataType: 'json',
            success: function(data){
				
					
					$('#user_details').append('<li>User '+  '-> Username: ' +data.username + ', password: ' +data.password+ ', city: '+ data.city + ',email : '+ data.email+', phone: '+data.phone+',news for user: '+ data.news + '; </li>' );
					
			
				
            },
            error: function(){
			
				$('#user_details').append('<li>Error !</li>' );
              
            }

        });
		
	});
		
	$("#find_city_hotels").click(function() {
		
	
		var city_search = $('#city_search').val();
		
		$.ajax({
            type: 'GET',
            url: 'http://localhost:8080/hotel/findByCity?city='+city_search,
            dataType: 'json',
            success: function(data){
				
					
				$.each(data,function(i,hotel) {
					
					$('#hotels_found').append('<li>' + i+'->Hotel ID: '+ hotel.hotelId+  '-> Hotel name: ' +hotel.hotelName + ', city: ' +hotel.city+ ', street: '+hotel.street + ', phone: '+hotel.phone+'; </li>' );
						
						hotel_id[i]= hotel.hotelId;
						hotel_name[i] = hotel.hotelName;
						hotel_city[i] = hotel.city;
					    hotel_street[i] = hotel.street;
						hotel_phone[i] = hotel.phone;
					
				});
					
			
				
            },
            error: function(){
			
				$('#hotels_found').append('<li>Sorry! We have no hotels in that city !</li>' );
              
            }

        });
		
	});
	
		$("#find_name_hotel").click(function() {
		
	
		var hotel_search = $('#hotel_search').val();
		
		$.ajax({
            type: 'GET',
            url: 'http://localhost:8080/hotel/findBySearchString?searchString='+hotel_search,
            dataType: 'json',
            success: function(data){
				
					
				$.each(data,function(i,hotel) {
					
					$('#hotels_found_name').append('<li>' + i+'->Hotel ID: '+ hotel.hotelId+  '-> Hotel name: ' +hotel.hotelName + ', city: ' +hotel.city+ ', street: '+hotel.street + ', phone: '+hotel.phone+'; </li>' );
						
						hotel_id[i]= hotel.hotelId;
						hotel_name[i] = hotel.hotelName;
						hotel_city[i] = hotel.city;
					    hotel_street[i] = hotel.street;
						hotel_phone[i] = hotel.phone;
					
				});
					
			
				
            },
            error: function(){
			
				$('#hotels_found_name').append('<li>Sorry! We have no hotels with that name/names !</li>' );
              
            }

        });
		
	});
	
	$("#low_cost_rooms").click(function() {
		
	
		var low_cost = $('#low_cost').val();
		
		$.ajax({
            type: 'GET',
            url: 'http://localhost:8080/rooms/filterByPrice/'+low_cost,
            dataType: 'json',
            success: function(data){
				
					
				$.each(data,function(i,room) {
					
					$('#low_cost_rooms_found').append('<li>Room '+ i+ '-> Id : ' +room.roomId + ', price per night: ' +room.pricePerNight +   ', floor : '+room.floor + ', room Number: ' + room.roomNb + ', capacity :'+ room.capacity+ ', FREE :'+ 
								room.free+ ', Hotel id:'+ room.hotel.hotelId + ', Hotel name: '+ room.hotel.hotelName+ ', city: '+ room.hotel.city +  '; </li>'  );
						

					
				});
					
			
				
            },
            error: function(){
			
				$('#low_cost_rooms_found').append('<li>Sorry! We have no rooms with selected criteria! </li>' );
              
            }

        });
		
	});
	
		$("#floor_rooms").click(function() {
		
	
		var floor_wanted = $('#floor_wanted').val();
		
		$.ajax({
            type: 'GET',
            url: 'http://localhost:8080/rooms/findByFloor?floor='+floor_wanted,
            dataType: 'json',
            success: function(data){
				
					
				$.each(data,function(i,room) {
					
					$('#floor_rooms_found').append('<li>Room '+ i+ '-> Id : ' +room.roomId + ', price per night: ' +room.pricePerNight +   ', floor : '+room.floor + ', room Number: ' + room.roomNb + ', capacity :'+ room.capacity+ ', FREE :'+ 
								room.free+ ', Hotel id:'+ room.hotel.hotelId + ', Hotel name: '+ room.hotel.hotelName+ ', city: '+ room.hotel.city +  '; </li>'  );
						

					
				});
					
			
				
            },
            error: function(){
			
				$('#floor_rooms_found').append('<li>Sorry! We have no rooms with selected criteria! </li>' );
              
            }

        });
		
	});
	
		$("#capacity_rooms").click(function() {
		
	
		var capacity_wanted = $('#capacity_wanted').val();
		
		$.ajax({
            type: 'GET',
            url: 'http://localhost:8080/rooms/findByCapacity?capacity='+capacity_wanted,
            dataType: 'json',
            success: function(data){
				
					
				$.each(data,function(i,room) {
					
					$('#capacity_rooms_found').append('<li>Room '+ i+ '-> Id : ' +room.roomId + ', price per night: ' +room.pricePerNight +   ', floor : '+room.floor + ', room Number: ' + room.roomNb + ', capacity :'+ room.capacity+ ', FREE :'+ 
								room.free+ ', Hotel id:'+ room.hotel.hotelId + ', Hotel name: '+ room.hotel.hotelName+ ', city: '+ room.hotel.city +  '; </li>'  );
						

					
				});
					
			
				
            },
            error: function(){
			
				$('#capacity_rooms_found').append('<li>Sorry! We have no rooms with selected criteria! </li>' );
              
            }

        });
		
	});
	
	$("#hotel_rooms").click(function() {
		
	
		var hotelname_rooms = $('#hotelname_rooms').val();
		
		$.ajax({
            type: 'GET',
            url: 'http://localhost:8080/rooms/findByHotel?hotelName='+hotelname_rooms,
            dataType: 'json',
            success: function(data){
				
					
				$.each(data,function(i,room) {
					
					$('#hotel_rooms_found').append('<li>Room '+ i+ '-> Id : ' +room.roomId + ', price per night: ' +room.pricePerNight +   ', floor : '+room.floor + ', room Number: ' + room.roomNb + ', capacity :'+ room.capacity+ ', FREE :'+ 
								room.free+ ', Hotel id:'+ room.hotel.hotelId + ', Hotel name: '+ room.hotel.hotelName+ ', city: '+ room.hotel.city +  '; </li>'  );
						

					
				});
					
			
				
            },
            error: function(){
			
				$('#hotel_rooms_found').append('<li>Sorry! We have no rooms with selected criteria! </li>' );
              
            }

        });
		
	});
	
	$("#free_rooms").click(function() {
		
	
	
		
		$.ajax({
            type: 'GET',
            url: 'http://localhost:8080/rooms/findFreeRooms',
            dataType: 'json',
            success: function(data){
				
					
				$.each(data,function(i,room) {
					
					$('#free_rooms_found').append('<li>Room '+ i+ '-> Id : ' +room.roomId + ', price per night: ' +room.pricePerNight +   ', floor : '+room.floor + ', room Number: ' + room.roomNb + ', capacity :'+ room.capacity+ ', FREE :'+ 
								room.free+ ', Hotel id:'+ room.hotel.hotelId + ', Hotel name: '+ room.hotel.hotelName+ ', city: '+ room.hotel.city +  '; </li>'  );
						

					
				});
					
			
				
            },
            error: function(){
			
				$('#free_rooms_found').append('<li>Sorry! We have no rooms with selected criteria! </li>' );
              
            }

        });
		
	});
	
	$("#reservations_check").click(function() {
		
	
	
		
		$.ajax({
            type: 'GET',
            url: 'http://localhost:8080/reservation/getall',
            dataType: 'json',
            success: function(data){
				
					
				$.each(data,function(i,res) {
					
					$('#reservations').append('<li>Reservation '+ i+ ', name:'+ res.user.username + ', check in date: '+ res.inDate + ', check out date:' + res.outDate + ', for room with id:'+ res.room.roomId +
						', price per night: ' +res.room.pricePerNight +   ', floor : '+res.room.floor + ', room Number: ' + res.room.roomNb + ', capacity :'+ res.room.capacity+ '; </li>'  );
						

					
				});
					
			
				
            },
            error: function(){
			
				$('#reservations').append('<li>Sorry! No reservations! </li>' );
              
            }

        });
		
	});
	
	$("#show_rooms").click(function() {
		
		findRoom_function();
		
		$.ajax({
            type: 'GET',
            url: 'http://localhost:8080/rooms/getall',
            dataType: 'json',
            success: function(data){
				$.each(data,function(i,room) {
					
					$('#rooms_for_res').append('<li>Room '+ i +'-> Id : ' +room.roomId + ', price per night: ' +room.pricePerNight +   ', floor : '+room.floor + ', room Number: ' + room.roomNb + ', capacity :'+ room.capacity+ ', FREE :'+ 
								room.free+ ', Hotel id:'+ room.hotel.hotelId + ', Hotel name: '+ room.hotel.hotelName+ ', city: '+ room.hotel.city + '; </li>' );
					
				});
				
            },
            error: function(){
			
				$('#rooms_for_res').append('<li>Error !</li>' );
              
            }

        });
		
	});
	
	$("#show_premium").click(function() {
		
		findRoom_function();
		
		$.ajax({
            type: 'GET',
            url: 'http://localhost:8080/rooms/getallPremium',
            dataType: 'json',
            success: function(data){
				$.each(data,function(i,room) {
					
					$('#premium_rooms').append('<li>Room '+ i +'-> Id : ' +room.roomId + ', price per night: ' +room.pricePerNight +   ', floor : '+room.floor + ', room Number: ' + room.roomNb + ', capacity :'+ room.capacity+ ', FREE :'+ 
								room.free+ ', Hotel id:'+ room.hotel.hotelId + ', Hotel name: '+ room.hotel.hotelName+ ', city: '+ room.hotel.city + '; </li>' );
					
				});
				
            },
            error: function(){
			
				$('#premium_rooms').append('<li>Error !</li>' );
              
            }

        });
		
	});
		
	$("#show_regular").click(function() {
		
		findRoom_function();
		
		$.ajax({
            type: 'GET',
            url: 'http://localhost:8080/rooms/getallRegular',
            dataType: 'json',
            success: function(data){
				$.each(data,function(i,room) {
					
					$('#regular_rooms').append('<li>Room '+ i +'-> Id : ' +room.roomId + ', price per night: ' +room.pricePerNight +   ', floor : '+room.floor + ', room Number: ' + room.roomNb + ', capacity :'+ room.capacity+ ', FREE :'+ 
								room.free+ ', Hotel id:'+ room.hotel.hotelId + ', Hotel name: '+ room.hotel.hotelName+ ', city: '+ room.hotel.city + '; </li>' );
					
				});
				
            },
            error: function(){
			
				$('#regular_rooms').append('<li>Error !</li>' );
              
            }

        });
		
	});
		
	$("#show_conference").click(function() {
		
			findRoom_function();
		
		$.ajax({
            type: 'GET',
            url: 'http://localhost:8080/rooms/getallConference',
            dataType: 'json',
            success: function(data){
				$.each(data,function(i,room) {
					
					$('#conference_rooms').append('<li>Room '+ i +'-> Id : ' +room.roomId + ', price per night: ' +room.pricePerNight +   ', floor : '+room.floor + ', room Number: ' + room.roomNb + ', capacity :'+ room.capacity+ ', FREE :'+ 
								room.free+ ', Hotel id:'+ room.hotel.hotelId + ', Hotel name: '+ room.hotel.hotelName+ ', city: '+ room.hotel.city + '; </li>' );
					
				});
				
            },
            error: function(){
			
				$('#conference_rooms').append('<li>Error !</li>' );
              
            }

        });
		
	});
	
		function findRoom_function(){  
           
			$.ajax({
            type: 'GET',
            url: 'http://localhost:8080/rooms/getall',
            dataType: 'json',
            success: function(data){
				$.each(data,function(i,room) {
					
						room_id[i]= room.roomId;
						room_price[i] = room.pricePerNight;
						room_floor[i] =room.floor ;
						room_number[i] = room.roomNb;
						room_capacity[i] =room.capacity;
						room_free[i] = room.free;
			
					
				});
				
            },
            error: function(){
			
				//$('#hotels').append('<li>Error !</li>' );
              
            }

        });
    }

	
	$("#book_room").click(function() {
		
	

		var username_local = localStorage.getItem("username");
		var city_local = localStorage.getItem("city");
		var phone_local = localStorage.getItem("phone");
		var email_local = localStorage.getItem("email");
		var password_local = localStorage.getItem("password");
	
		var roomid_book = $('#roomid_book').val();
		var checkin_date = $('#checkin_date').val();
		var checkout_date = $('#checkout_date').val();
		
	
		
		var index=0;
		for (var i=0; i < room_id.length; i++) {             
				if(room_id[i] == roomid_book)
				{
					index = i;
				}
  
		}
	
		$.ajax({
            type: 'POST',
            url: 'http://localhost:8080/reservation/create',
			dataType: 'json',
			data: JSON.stringify({
									inDate:checkin_date,
									outDate:checkout_date,
									user:
									{
										username:username_local,
										password:password_local,
										phone:phone_local,
										email:email_local,
										city:city_local

									},
									room:
									{
										roomId:roomid_book,
										pricePerNight:room_price[index],
										floor:room_floor[index],
										roomNb:room_number[index],
										capacity:room_capacity[index],
										free:room_free[index]
										
									}
						
								}),
			contentType: "application/json; charset=utf-8",
            success: function(data){
				
					
					$('#booked').append('<li>Book for user: ' + username_local + ', checkin_date : ' + checkin_date+ ', check out date: ' + checkout_date + ' for room with id: ' + roomid_book +
						' was successfully made:'+ '</li>' );
					
		
				
            },
            error: function(){
			
				$('#booked').append('<li>Room not free in that period!</li>' );
              
            }

        });
		
	});
	

	$("#show_books").click(function() {
		
			
		var username_local = localStorage.getItem("username");
		
		$.ajax({
            type: 'GET',
            url: 'http://localhost:8080/reservation/getallUsername/'+username_local,
            dataType: 'json',
            success: function(data){
				$.each(data,function(i,book) {
					
					$('#my_books').append('<li>Book ID:' +book.id +' for room id '+book.room.roomId +', price per night: '+book.room.pricePerNight+ ', checkin_date : ' + book.inDate+ ', check out date: ' + book.outDate + ', for user '+book.user.username + '; </li>' );
					
				});
				
            },
            error: function(){
			
				$('#my_books').append('<li>Error !</li>' );
              
            }

        });
		
	});
	
	$("#book_update").click(function() {
		
	

		var username_local = localStorage.getItem("username");
		var city_local = localStorage.getItem("city");
		var phone_local = localStorage.getItem("phone");
		var email_local = localStorage.getItem("email");
		var password_local = localStorage.getItem("password");
	
		var id_book = $('#id_book').val();
		var new_roomid_book = $('#new_roomid_book').val();
		var new_checkin_date = $('#new_checkin_date').val();
		var new_checkout_date = $('#new_checkout_date').val();
		
	
		
		var index=0;
		for (var i=0; i < room_id.length; i++) {             
				if(room_id[i] == new_roomid_book)
				{
					index = i;
				}
  
		}
	
		$.ajax({
            type: 'PUT',
            url: 'http://localhost:8080/reservation/update/'+id_book,
			dataType: 'json',
			data: JSON.stringify({
									inDate:new_checkin_date,
									outDate:new_checkout_date,
									user:
									{
										username:username_local,
										password:password_local,
										phone:phone_local,
										email:email_local,
										city:city_local

									},
									room:
									{
										roomId:new_roomid_book,
										pricePerNight:room_price[index],
										floor:room_floor[index],
										roomNb:room_number[index],
										capacity:room_capacity[index],
										free:room_free[index]
										
									}
						
								}),
			contentType: "application/json; charset=utf-8",
            success: function(data){
				
					
					$('#booked_modified').append('<li>Book for user: ' + username_local + ', checkin_date : ' + new_checkin_date+ ', check out date: ' + new_checkout_date + ' for room with id: ' + new_roomid_book +
						' was successfully modified:'+ '</li>' );
					
		
				
            },
            error: function(){
			
				$('#booked_modified').append('<li>Room not free in that period!</li>' );
              
            }

        });
		
	});
	
	
	$("#cancel_book").click(function() {
		
		var delete_id_book = $("#delete_id_book").val();

		
		$.ajax({
            type: 'DELETE',
            url: 'http://localhost:8080/reservation/deletebyid/'+delete_id_book,
            dataType: 'json',
            success: function(data){
			
					$('#deleted_book').append('<li>Book with id:  '+delete_id_book+ '  deleted successful! </li>' );
					
				
				
            },
            error: function(){
			
				$('#deleted_book').append('<li>Book not found !</li>' );
              
            }
    

        });
		
	});
	
	$("#delete_reservations").click(function() {
		
	

		
		$.ajax({
            type: 'DELETE',
            url: 'http://localhost:8080/reservation/deleteall',
            dataType: 'json',
            success: function(data){
			
					$('#deleted_reservations').append('<li>All system books deleted successful! </li>' );
					
				
				
            },
            error: function(){
			
				$('#deleted_reservations').append('<li>Book not found !</li>' );
              
            }
    

        });
		
	});
	
	
	
	
});