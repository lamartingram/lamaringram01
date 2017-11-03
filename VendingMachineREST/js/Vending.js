$(document).ready(function () {
loadItems();
 	 		 	    //INITIALIZE THE VENDING MACHINE
 	 		 	    $('#change-out').hide(); 
 	 		 	    $('#message-out').text("Please Buy A Item We Need Money");
 	 		 	        var totalMoney;
 	 		 	    $('#total-money').text(0);
 	 		 	    
 	 		 	    //The add money buttons
 	 		 	    $('.add-money-btn').on('click',function(){
 	 		 	            totalMoney=parseFloat($('#total-money').text());
 	 		 	            totalMoney += parseFloat(($(this).attr("value")));
 	 		 	            $('#total-money').text(totalMoney.toFixed(2));       
 	 		 	            resetChangeBox();
 	 		 	    });
 	 		 	
 	 		 	   //Clicking on Items buttons
 	 		 	        $('.vend-btn').on('click',function(event){                      
 	 		 	            var itemNum = parseInt(this.id.charAt(1));  //Index of One                 
 	 		 	            $('#item-input').val(itemNum); //SET VALUE OF ITEM INPUT FIELD WHEN CICKED 
 	 		 	            resetChangeBox();
 	 		 	        });
 	 		 	
 	 		 	 
 	 		 	    // Return Money
 	 		 	    $('#return-change-button').on('click',function(event){          
 	 		 	            changeReturn();
 	 		 	            $('#total-money').text(0);  //RESEST USER MONEY TO 0, AS WE ARE CANCELING PREV TRANSACTION
 	 		 	    });
 	 		 	
 	 		 	    // Make a Purchase
 	 		 	        $('#make-purchase-button').on('click',function(event){
 	 		 	            var selectedItem = parseInt($('#item-input').val());
 	 		 	            totalMoney = totalMoney=parseFloat($('#total-money').text());
 	 		 	            if (validateUserInput(selectedItem)){
 	 		 	                   //IF USER INPUT IS VALID MAKE AJAX CALL!!                    
 	 		 	                vendItem(selectedItem, totalMoney);
 	 	 	                loadItems();
 	 		 	            }
 	 		 	        });
 	 		 	                
 	 		 	});
 	 		 	
 	 		 	//Get all the Vending Machine products from the service 
 	 		 	function loadItems(){
 	 		 	        $.ajax({
 	 		 	        type: 'GET',
 	 		 	        url: 'http://localhost:8080/items',
 	 		 	        success: function(itemArray) { 
 	 		 	                console.log("success!");
 	 		 	           
 	 		 	            $.each(itemArray, function(index, item){ //Go through each Item 
 	 		 	                var id=item.id;
 	 		 	                var name = item.name;
 	 		 	                var price = item.price;
 	 		 	                var quantity = item.quantity;
 	 		 	
 	 		 	                var printName = '#name' + id;
 	 		 	                var printPrice = '#price' + id;
 	 		 	                var printQuantity = '#qty' + id;
 	 		 	                
 	 		 	                $(printName).text(name); //Display value to BUTTON TEXT
 	 		 	                $(printPrice).text('$' + price.toFixed(2));
 	 		 	                $(printQuantity).text("Quantity Left: " + quantity);
 	 		 	
 	 		 	            });
 	 		 	        },
 	 		 	        error: function() {
 	 		 	            $('#message-out')
 	 		 	                    .attr({class: 'text-danger text-center'})
 	 		 	                    .text('Out of service.  Please try again later.'); //DISPALY IF WEB SERVICE CANNOT BE REACHED
 	 		 	        }
 	 		 	    });
 	 		 	}
 	 		 	
 	 		 	//PURCHASE AN ITEM
 	 		 	function vendItem(id, totalMoney){
 	 		 	        
 	 		 	        var Url = 'http://localhost:8080/money/' + totalMoney + '/item/' + id;
 	 		 	        var change;
 	 		 	
 	 		 	        $.ajax({
 	 		 	        type: 'GET',
 	 		 	        url: Url,
 	  	                success: function(data, status) { 
 	 		 	                $('#change-out').empty();
 	 		 	                var quarters = data.quarters;
 	 		 	                var dimes = data.dimes;
 	 		 	                var nickels = data.nickels;
 	 		 	                var pennies = data.pennies;
 	 		 	               
 	 		 	                
 	 		 	            returnChange(quarters, dimes, nickels , pennies );
 	 		 	            $('#message-out').removeClass('text-danger'); // Error when not enough money or Insuffiecent funds 
 	 		 	            $('#message-out').text("Thank you!"); 
 	 		 	            totalMoney = 0;//After Transaction clear money from Vending Machine
 	 		 	            $('#total-money').text(0);
 	 		 	
 	 		 	        },
 	 		 	        error: function(data) {
 	 		 	            $('#message-out')
 	 		 	                    .attr({class: 'text-danger text-center'})
 	 		 	                    .text($.parseJSON((data.responseText)).message);//GET MESSAGE FROM WEB SERIVCE
 	 		 	        }
 	 		 	    });
 	 		 	}
 	 		 	
 	 		 	function changeReturn(){
 	 		 	    loadItems();
 	 		 	    //IF CHANGE OUT IS HIDDEN, THIS MEANS THERE WAS NOT YET A SUCCESSFUL AJAX CALL
 	 		 	   
 	 		 	    if (!($('#change-out').is(':visible'))) {
 	 		 	        //TRANSACTION IS CANCELED, RETURN MONEY TO THE USER
 	 		 	        $('#change-out').empty();
 	 		 	        var returnMoney = parseFloat($('#total-money').text());
 	 		 	        var returnCents = returnMoney * 100;
 	 		 	        var remaining = 0;
 	 		 	        var returnQuarters = 0;
 	 		 	        var returnDimes = 0;
 	 		 	        var returnNickels = 0;
 	 		 	        var returnPennies = 0;
 	 		 	        var moneyInQuarters = 0;
 	 		 	        var moneyInDimes = 0;
 	 		 	        var moneyInNickels = 0;
 	 		 	        var moneyInPennies = 0;
 	 		 	
 	 		 	        returnQuarters = Math.floor(returnCents/25);
 	 		 	        moneyInQuarters = returnQuarters * 25;
 	 		 	        remaining = returnCents - moneyInQuarters;
 	 		 	
                        returnDimes = Math.floor(remaining/10);
 	 		 	        moneyInDimes = returnDimes * 10;
 	 		 	        remaining -= moneyInDimes;
 	 		 	
 	 		 	        returnNickels = Math.floor(remaining/5);
 	 		 	        moneyInNickels = returnNickels * 5;
 	 		 	        remaining -= moneyInNickels;


 	 		 	        returnPennies = Math.floor(remaining/ 1 );
 	 		 	        
 	 		 	        
 	 		 	        returnChange(returnQuarters, returnDimes, returnNickels , returnPennies);

 	 		 	    } else {  //THIS MEANS THAT THERE WAS JUST A SUCCESSFUL TRANSACTION, AND WE WANT TO CLEAR OUT
 	 		 	       $('#change-out').empty(); 
 	 		 	    }
 	 		 	    //RESET FIELDS
 	 		 	    $('#message-out').text('');
 	 		 	    $('#total-money').text(0);
 	 		 	    $('#item-input').val(''); 
 	 		 	}
 	 		 	
 	 		 	//CHECK THE INPUT FIELD FOR RANGE AND PROPER INPUT
 	 		 	function validateUserInput(selectedItem){
 	 		 	    if(selectedItem > 9 || selectedItem < 1 || isNaN(selectedItem)) {
 	 		 	        $('#item-input').val('');
 	 		 	        $('#message-out').attr({class: 'text-danger text-center'});
 	 		 	        $('#message-out').text('Please select A Item between 1-9');
 	 		 	        return false;
 	 		 	    } else {
 	 		 	        return true;
 	 		 	    }
 	 		 	}
 	 		 	
 	 		 	//Reset Everthing back to Zero
 	 		 	function resetChangeBox(){
 	 		 	    $('#change-out').empty();   
 	 		 	    $('#change-out').hide(); 
 	 		 	    $('#message-out').removeClass('text-danger'); 
 	 		 	    $('#message-out').text("We neeed money Make Another Purchase"); 
 	 		 	
 	 		 	}
 	 		 	
 	 		 	function returnChange(quarters, dimes, nickels , pennies) {
 	 		 	
 	 		 	    if (quarters > 0){
 	 		 	        $('#change-out').append('QUARTERS: ' + quarters + '<br>');
 	 		 	    }
 	 		 	    if (dimes > 0) {
 	 		 	        $('#change-out').append('DIMES: ' + dimes + '<br>');
 	 		 	    }
 	 		 	    if (nickels > 0) {
 	 		 	        $('#change-out').append('NICKELS: ' + nickels + '<br>');
 	 		 	    }
 	 		 	    if ( pennies > 0 ) {
 	 		 	    	$('#change-out').append('PENNIES: ' + pennies + '<br>');
 	 		 	    }
 	 		 	    $('#change-out').show(); //SHOW THE CHANGE TO BE RETURNED
 	 		 	}
 	 		 	