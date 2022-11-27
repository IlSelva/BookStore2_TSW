function ricerca(str) { //str è la stringa digitata dall'utente
    var dataList = document.getElementById('ricerca-datalist'); //carico dal documento il datalist utilizzando l'id
    if (str.length == 0) {
        dataList.innerHTML = '';
        return;
    }

    var xmlHttpReq = new XMLHttpRequest();
    xmlHttpReq.responseType = 'json'; //setto il tipo di risposta a json
    xmlHttpReq.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            dataList.innerHTML = '';
            for (var i in this.response) { // for sulla risposta inviata dal server, in questo caso è un JSONArray
                var option = document.createElement('option');
                option.value = this.response[i];
                dataList.appendChild(option);
            }
        }

    }
    xmlHttpReq.open("GET", "RicercaAjax?q=" + encodeURIComponent(str), true);
    xmlHttpReq.send();

}