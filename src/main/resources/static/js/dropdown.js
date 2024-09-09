// dropdown.js
$(document).ready(function() {

    // Fetch States based on Country selection
    $('#country').on('change', function() {
        var countryId = $(this).val();
        if (countryId) {
            $.ajax({
                url: "/states/" + countryId,
                type: "GET",
                success: function(states) {
                    $('#state').empty();  // Clear previous state options
                    $('#state').append('<option value="">Select State</option>');
                    $.each(states, function(key, value) {
                        $('#state').append(`<option value="${key}">${value}</option>`);
                    });
                }
            });
        } else {
            $('#state').empty().append('<option value="">Select State</option>'); // Reset state dropdown
        }
        $('#city').empty().append('<option value="">Select City</option>'); // Reset city dropdown
    });

    // Fetch Cities based on State selection
    $('#state').change(function() {
        var stateId = $(this).val();
        if (stateId) {
            $.ajax({
                url: "/cities/" + stateId,
                type: "GET",
                success: function(cities) {
                    $('#city').empty();  // Clear previous city options
                    $('#city').append('<option value="">Select City</option>');
                    $.each(cities, function(key, value) {
                        $('#city').append('<option value="' + key + '">' + value + '</option>');
                    });
                }
            });
        } else {
            $('#city').empty().append('<option value="">Select City</option>'); // Reset city dropdown
        }
    });
});
