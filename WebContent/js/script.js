//add_to_cart function 
function add_to_cart(pid,pname,price){
	
	let cart = localStorage.getItem("cart");
	if(cart == null){
		
		//no cart yet
		let products=[];
		let product={ productId:pid,productName:pname,productQuantity:1,productPrice:price};
		
		products.push(product);
		localStorage.setItem("cart",JSON.stringify(products)); /*JSON.stringify convert products array to string*/
		
		//Now give the message
		//console.log("Product is added for the first time");
		
		//Message by using Toast
		showToast("Item is added to cart")
		
	}else{
		
		//cart is already present
		let pcart = JSON.parse(cart); //We convert the string into javascript array by using JSON.parse()
		
		let oldProduct = pcart.find((item)=>item.productId == pid);
		
		if(oldProduct){
			
			//Only we have to increase the quantity
			oldProduct.productQuantity = oldProduct.productQuantity+1;
			pcart.map((item) => {
				
				if(item.productId === oldProduct.productId){
					item.productQuantity = oldProduct.productQuantity;
				}
				
			})
			localStorage.setItem("cart",JSON.stringify(pcart));
			
			console.log("Product Quantity is increased");
			
			//Message by using Toast
			showToast(oldProduct.productName + " Quantity is increased , Quantity = " + oldProduct.productQuantity)
			
		}else{
			//We have to add the product
			let product={ productId:pid,productName:pname,productQuantity:1,productPrice:price};
			pcart.push(product);
			localStorage.setItem("cart",JSON.stringify(pcart));
			console.log("Product is added");
			
			//Message by using Toast
			showToast("Product is added to cart")
			
		}
	}
	
	updateCart();
		
}

//Update cart function :
function updateCart()
{
	//now fatch or get the cart from localStorage
	let cartString = localStorage.getItem("cart");
	//cartString comes in the form of string so
	//first convert cartString from string to object using JSON.parse(cartString)
	let cart = JSON.parse(cartString); //now it is come in normal cart object
	
	if(cart == null || cart.length == 0){
		console.log("Cart is empty !!");
		//Jquery
		$(".cart-items").html("( 0 )");
		$(".cart-body").html("<h3>Cart does not have any items</h3>");
		$(".checkout-btn").attr('disabled',true);
	}else{
		
		//There is something in cart to show
		console.log(cart);
		
		//fetch the item
		$(".cart-items").html(`(${cart.length})`); //This shows value in the cart
		
		//table
		let table = `
			<table class='table'>
			<thead class='thead-light'>
				<tr>
					<th>Item Name</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Total Price</th>
					<th>Action</th>
					
				</tr>
			</thead>
			
				
				
		`;
		
		let totalPrice = 0;
		
		cart.map((item)=>{
			//append
			table +=`
				<tr>
					<td>${item.productName}</td>
					<td>${item.productPrice}</td>
					<td>${item.productQuantity}</td>
					<td>${item.productQuantity * item.productPrice}</td>
					<td><button onclick='deleteItemFromCart(${item.productId})' class='btn btn-danger btn-sm'>Remove</button></td>
				</tr>
			
			`
			totalPrice+= item.productPrice * item.productQuantity;
			
		})
		
		
		table = table + `
			<tr><td colspan='5' class='text-right font-weight-bold m-5'> Total Price : ${totalPrice} </td></tr>
		
		</table>`
		$(".cart-body").html(table);
		
		//Remove disabled class
		$(".checkout-btn").attr('disabled',false);
		
		console.log("removed")
	}
}

//Delete item
function deleteItemFromCart(pid)
{	//get cart
	let cart = JSON.parse(localStorage.getItem(`cart`));
	
	//filter
	let newcart = cart.filter((item)=>item.productId != pid)
	
	//store into localStorage
	localStorage.setItem(`cart`,JSON.stringify(newcart))
	
	//updateCart
	updateCart();
	
	//Message by using Toast
	showToast("Item is removed from cart")
	
}


$(document).ready(function (){
	updateCart();
})

function showToast(content){
	$("#toast").addClass("display");
	$("#toast").html(content);
	setTimeout(() => {
		$("#toast").removeClass("display");
	}, 2000);
}

//goToCheckout() function
function goToCheckout(){
		window.location="checkout.jsp"
	
}