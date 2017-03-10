var btnGetNewQuote = document.getElementById("btnGetNewQuote");
var textfieldQuote = document.getElementById("textfieldQuote");
var output = document.getElementById("output");
var displayedQuote;
var quoteId;
btnGetNewQuote.addEventListener("click",getNewQuote);

function getNewQuote(){
    var url = "http://localhost:8084/61Rest/api/quote/random";
    var conf = {method: 'get'};
    var promise = fetch(url, conf);
        promise.then(function(response){
            return response.text();
        }).then(function(text){
            var returnedQuote = text.split(":");
            quoteId = returnedQuote[0];
            var quoteText = returnedQuote[1];
            document.getElementById("textfieldQuote").value = quoteText;//output quote
            output.innerHTML = "";//clear feedback
        }); 
}

function getSpecificQuote(){
    var inputQuoteNumber = document.getElementById("inputQuoteNumber").value;
    var url = "http://localhost:8084/61Rest/api/quote/"+inputQuoteNumber;
    var conf = {method: 'get',
        headers: {
            'Content-Type': 'application/json'
            }};
    var promise = fetch(url, conf);
        promise.then(function(response){
            return response.text();
        }).then(function(text){
            var returnedQuote = text.split(":");
            quoteId = returnedQuote[0];
            var quoteText = returnedQuote[1];
            document.getElementById("textfieldQuote").value = quoteText;//output quote
            output.innerHTML = "";//clear feedback
            document.getElementById("inputQuoteNumber").value = "";
        });
//        promise.catch(function(text){
            //document.getElementById("textfieldQuote").value = "hej";//output quote
            //document.getElementById("textfieldQuote").value = response.text();//output quote
//            document.getElementById("textfieldQuote").value = response.text();//output quote
            //document.getElementById("textfieldQuote").value = text;//output quote
//        });
}


function addQuote(){
    if(textfieldQuote.value.length>=1){
        var url = "http://localhost:8084/61Rest/api/quote";
        var conf = {
            method: 'post',
            headers: {
            'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                quote: textfieldQuote.value
            })
        };
        var promise = fetch(url, conf);
            promise.then(function(response){
                return response.text();
            }).then(function(text){
                output.innerHTML = text;//output feedback
                textfieldQuote.value = "";//clear textarea
            }); 
    }
    else if(textfieldQuote.value.length<1){
        output.innerHTML = "Please input some text";
    }
}

function editQuote(){
    var url = "http://localhost:8084/61Rest/api/quote/"+quoteId;
    var conf = {
        method: 'put',
        headers: {
        'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            quote: textfieldQuote.value
        })
    };
    var promise = fetch(url, conf);
        promise.then(function(response){
            return response.text();
        }).then(function(text){
            output.innerHTML = text;//output feedback
            textfieldQuote.value = "";//clear textarea
        }); 
}
   
function deleteQuote(){
    var url = "http://localhost:8084/61Rest/api/quote/"+quoteId;
    var conf = {method: 'delete'};
    var promise = fetch(url, conf);
        promise.then(function(response){
            return response.text();
        }).then(function(text){
            output.innerHTML = text;//output feedback
            textfieldQuote.value = "";//clear textarea
        }); 
}