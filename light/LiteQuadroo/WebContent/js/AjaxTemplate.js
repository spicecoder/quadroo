$(
	function(){
		// Get a reference to the content div (into which we will load content).
		var jContent = $( "#content" );
						
		// Hook up link click events to load content.
		$( "a" ).click(
			function( objEvent ){
				var jLink = $( this );
				
				// Clear status list.
				$( "#ajax-status" ).empty();
				
				// Launch AJAX request.
				$.ajax(
					{
						// The link we are accessing.
						url: jLink.attr( "href" ),
						
						// The type of request.
						type: "get",
						
						// The type of data that is getting returned.
						dataType: "html",
						
						error: function(){
							ShowStatus( "AJAX - error()" );
							
							// Load the content in to the page.
							jContent.html( "<p>Page Not Found!!</p>" );
						},
						
						beforeSend: function(){
							ShowStatus( "AJAX - beforeSend()" );
						},
						
						complete: function(){
							ShowStatus( "AJAX - complete()" );
						},
						
						success: function( strData ){
							ShowStatus( "AJAX - success()" );
							
							// Load the content in to the page.
							jContent.html( strData );
						}
					}							
					);
				
				// Prevent default click.
				return( false );					
			}
			);
		
	}