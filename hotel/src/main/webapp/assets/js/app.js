// Top Menu Navigation
$($(".nav .topli").children("a")).on("click", function(){
   $(".nav").find(".menu-top-active").removeClass("menu-top-active");
   $(this).addClass("menu-top-active");
});

//Calendar init script
$(function() {
    $('input[name="daterange"]').daterangepicker({
    	locale: {
    	      format: 'DD-MM-YYYY'
    	    },
    	    startDate: moment().format('DD-MM-YYYY'),
    	    endDate: moment().add(1,'days').format('DD-MM-YYYY'),
    	    minDate: moment().format('DD-MM-YYYY')
    }, 
    function(start, end, label) {
    	$('.hidden-start-date input[type=hidden]').val(start.format('DD-MM-YYYY') + ' 00:00');
    	$('.hidden-end-date input[type=hidden]').val(end.format('DD-MM-YYYY') + ' 00:00');
    	
    	$('.hiddenbutton').click();
    	$('#date-headline').text( start.format('DD/MM/YYYY') + ' - ' + end.format('DD/MM/YYYY'));
       // alert("A new date range was chosen: " + start.format('DD-MM-YYYY') + ' to ' + end.format('DD-MM-YYYY'));
    });
});

//Wizard Step

$(document).ready(function () {
	  var navListItems = $('div.setup-panel div a'),
	          allWells = $('.setup-content'),
	          allNextBtn = $('.nextBtn');

	  allWells.hide();

	  navListItems.click(function (e) {
	      e.preventDefault();
	      var $target = $($(this).attr('href')),
	              $item = $(this);

	      if (!$item.hasClass('disabled')) {
	          navListItems.removeClass('btn-primary').addClass('btn-default');
	          $item.addClass('btn-primary');
	          allWells.hide();
	          $target.show();
	          $target.find('input:eq(0)').focus();
	      }
	      
	      $("#guestname").text($(".guestname").val());
	      
	  });

	  allNextBtn.click(function(){
	      var curStep = $(this).closest(".setup-content"),
	          curStepBtn = curStep.attr("id"),
	          nextStepWizard = $('div.setup-panel div a[href="#' + curStepBtn + '"]').parent().next().children("a"),
	          curInputs = curStep.find("input[type='text'],input[type='url']"),
	          isValid = true;

	      $(".form-group").removeClass("has-error");
	      for(var i=0; i<curInputs.length; i++){
	          if (!curInputs[i].validity.valid){
	              isValid = false;
	              $(curInputs[i]).closest(".form-group").addClass("has-error");
	          }
	      }

	      if (isValid)
	          nextStepWizard.removeAttr('disabled').trigger('click');
	  });

	  $('div.setup-panel div a.btn-primary').trigger('click');
	});

