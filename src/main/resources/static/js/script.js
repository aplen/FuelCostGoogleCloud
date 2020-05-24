    (function main() {
//               const API_URL = 'https://wise-mantra-270807.appspot.com/api';
//               const URL = 'https://wise-mantra-270807.appspot.com';
        const API_URL = 'http://localhost:8080/api';
        const URL = 'http://localhost:8080';
        const fuelsList = document.getElementById('fuelsList');
        const carName = document.getElementById('carName');
        const pbOn100Km = document.getElementById('pbOn100Km');
        const lpgOn100Km = document.getElementById('lpgOn100Km');
        const onOn100Km = document.getElementById('onOn100Km');
        const kmOnPb = document.getElementById('kmOnPb');
        const kmOnLpg = document.getElementById('kmOnLpg');
        const kmOnOn = document.getElementById('kmOnOn');
        const pbPrice = document.getElementById('pbPrice');
        const lpgPrice = document.getElementById('lpgPrice');
        const onPrice = document.getElementById('onPrice');
        const confirmTxt = "Delete selected position?";
 fuelsList.addEventListener("change", fuelFieldsDisable);
                fuelFieldsDisable();
        var userAuthenticated = "";
        showDate();

        //display username
               fetch(`${URL}/users/loggedUser`)
                   .then(processOkUser)
                   .then(resp => resp.text())
                   .then(resp => {
                       document.getElementById('loggedUser').innerHTML = `Logged user: ${resp}`;
                       userAuthenticated = resp;
                       if (resp === "admin") {
                           document.getElementById("adminbtn").disabled = false;
                       }
                   });

        //"cars list" and "add new car" section


        document.getElementById('addCar').addEventListener('click', (event) => {
            if (onOn100Km.checkValidity() && pbOn100Km.checkValidity() && lpgOn100Km.checkValidity()) {
                event.preventDefault();
                fetch(`${API_URL}/cars`, {
                        method: 'POST',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({
                            name: carName.value,
                            username: userAuthenticated,
                            lpgPowered: !lpgOn100Km.disabled,
                            pbPowered: !pbOn100Km.disabled,
                            onPowered: !onOn100Km.disabled,
                            onOn100Km: onOn100Km.value,
                            lpgOn100Km: lpgOn100Km.value,
                            pbOn100Km: pbOn100Km.value
                        })
                    })
                    .then(processOkResponse)
                    .then(displayCar)
                    .then(fuelFieldsDisable)
                    .then(document.getElementById('addCarInfo').innerHTML = `<h3>car successfully saved in your database</h3>`)
                    .catch(console.warn);
            } else {
                document.getElementById('addCarInfo').innerHTML = `<h3>only integers or float with dot allowed (eg. 10 or 10.00)</h3>`;
            }
        });

        fetch(`${API_URL}/cars`)
            .then(processOkResponse)
            .then(cars => cars.forEach(displayCar));

        function displayCar(car) {
            if (car.username == userAuthenticated || car.username == "default user") {
                const label = document.createElement('label');
                label.classList.add('pure-checkbox');

                var useBtnTxt = document.createTextNode("use car");
                var delBtnTxt = document.createTextNode("delete car");
                var editBtnTxt = document.createTextNode("edit car");

                var deleteButton = document.createElement('button');
                deleteButton.classList.add('pure-button-primary');
                deleteButton.appendChild(delBtnTxt);
                deleteButton.addEventListener('click', (event) => {
                    event.preventDefault();
                    if (confirm(confirmTxt)&&car.username !=="default user") {
                        fetch(`${API_URL}/cars/${car.id}`, {
                                method: 'DELETE'
                            })
                            .then((response) => {
                                if (response.ok) {
                                    label.remove();
                                    return response.ok;
                                }
                                alert(`HTTP status not OK  (${response.status})`);
                                throw new Error(`Status not 200 (${response.status})`);
                            })
                            .catch(console.warn);
                    }
                });


                const useButton = document.createElement('button');
                useButton.classList.add('pure-button-primary');
                useButton.appendChild(useBtnTxt);

                useButton.addEventListener('click', (event) => {
                    event.preventDefault();
                    document.getElementById('twoColumns').remove();
                    document.getElementById('chosenCar').appendChild(document.createTextNode(` ${car.name}` + ': ON:' + ` ${car.onOn100Km}` + 'l/100km | PB:' +
                        ` ${car.pbOn100Km}` + 'l/100km | LPG:' + ` ${car.lpgOn100Km}` + 'l/100km'));
                    document.getElementById('welcome').innerHTML = `<h3>Actual Poland average fuel prices:</h3>`;
                    document.getElementById('mainForm').style.display = 'initial';
                    enableKmAndPriceFields(car);
                    sendData(car);
                });

                label.appendChild(document.createTextNode(` ${car.name}` + ': ON:' + ` ${car.onOn100Km}` + 'l/100km | PB:' +
                    ` ${car.pbOn100Km}` + 'l/100km | LPG:' + ` ${car.lpgOn100Km}` + 'l/100km'));
                var br = document.createElement("br");
                label.appendChild(br);
                label.appendChild(useButton);
                label.appendChild(document.createTextNode(' '));
                label.appendChild(deleteButton);
                document.getElementById('allCars').appendChild(label);


            }
        }

        document.getElementById('clearButton').addEventListener('click', (event) => {
            event.preventDefault();
            document.forms[1].reset();
        });

        //final window section
        function sendData(car) {

            document.getElementById('calculateButton').addEventListener('click', (event) => {
                event.preventDefault();
                if (kmOnLpg.checkValidity() && kmOnPb.checkValidity() && kmOnOn.checkValidity() && lpgPrice.checkValidity() && onPrice.checkValidity() && pbPrice.checkValidity()) {
                    const finishForm = document.getElementById('inputLast');
                    const tripDataObj = {};

                    fetch(`${API_URL}/fuelcost/${car.id}`, {
                            method: 'POST',
                            headers: {
                                'Accept': 'application/json',
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify({
                                kmOnLpg: finishForm.elements.kmOnLpg.value,
                                kmOnPb: finishForm.elements.kmOnPb.value,
                                kmOnOn: finishForm.elements.kmOnOn.value,
                                lpgPrice: finishForm.elements.lpgPrice.value,
                                pbPrice: finishForm.elements.pbPrice.value,
                                onPrice: finishForm.elements.onPrice.value,
                            })
                        })
                        .then(processOkResponse)
                        .then((text) => {
                            document.getElementById('result').innerHTML = `
                    <h2>The cost of trip is ${text}PLN!</h2>`;
                        })

                    .catch(console.warn);
                } else { document.getElementById('result').innerHTML = `
                                             <h2>only integers or float with dot allowed (eg. 10 or 10.00)</h2>`; }
            });
        }
               //users list view section
        document.getElementById('adminbtn').addEventListener('click', (event) => {
            event.preventDefault();
            fetch(`${URL}/users`)
            .then(document.getElementById("allUsers").innerHTML = '')
                .then(processOkResponse)
                .then(users => users.forEach(displayUser));

            function displayUser(appUser) {
                const label = document.createElement('label');
                label.style = "white-space: pre;";
                label.classList.add('pure-checkbox');

                var delTxt = document.createTextNode("delete user");
                const delUserButton = document.createElement('button');
                delUserButton.classList.add('pure-button-primary');
                delUserButton.appendChild(delTxt);
                delUserButton.addEventListener('click', (event) => {
                    event.preventDefault();
                    if (confirm("removing " + `${appUser.username}` + " from database") && `${appUser.username}` !== "admin") {
                        fetch(`${URL}/users/${appUser.username}`, {
                                method: 'DELETE'
                            })
                            .then((response) => {
                                if (response.ok) {
                                    label.remove();
                                    return response.ok;
                                }
                                alert(`HTTP status not OK  (${response.status})`);
                                throw new Error(`Status not 200 (${response.status})`);
                            })
                            .catch(console.warn);
                    }
                });
                label.appendChild(document.createTextNode('Name: ' + `${appUser.username}` + ',\nlang: ' + `${appUser.langId}` + ',\nrole: ' +
                    `${appUser.role}` + ',\npass: ' + `${appUser.password}`));
                label.appendChild(document.createElement("br"));
                label.appendChild(document.createTextNode(' '));
                label.appendChild(delUserButton);
                document.getElementById('allUsers').appendChild(label);
            }
            document.getElementById('usersSection').style = "display:inline;";
            document.getElementById('twoColumns').style = "display:none;";
        });


        //prices show section
        getActualPrices();

        function getActualPrices() {
            document.getElementById('showPrices').addEventListener('click', (event) => {
                event.preventDefault();
                fetch(`${API_URL}/prices`)
                    .then(processOkResponse)
                    .then(price => {
                        document.getElementById('addPrices').innerHTML = price;
                    })
            });
        }
        //other functions
        function fuelFieldsDisable() {
                   var fuelChosen = fuelsList.value;
                   if (fuelChosen == '') {
                       lpgOn100Km.disabled = true;
                       lpgOn100Km.value = '';
                       onOn100Km.disabled = true;
                       onOn100Km.value = "";
                       pbOn100Km.disabled = true;
                       pbOn100Km.value = "";
                   } else if (fuelChosen == "ON") {
                       lpgOn100Km.disabled = true;
                       lpgOn100Km.value = '';
                       onOn100Km.disabled = false;
                       pbOn100Km.disabled = true;
                       pbOn100Km.value = '';

                   } else if (fuelChosen == "PB") {
                       lpgOn100Km.disabled = true;
                       lpgOn100Km.value = '';
                       onOn100Km.disabled = true;
                       onOn100Km.value = '';
                       pbOn100Km.disabled = false;
                   } else if (fuelChosen == "LPG/PB") {
                       lpgOn100Km.disabled = false;
                       onOn100Km.disabled = true;
                       onOn100Km.value = '';
                       pbOn100Km.disabled = false;
                   }
               };

               function enableKmAndPriceFields(car) {
                   if (car.lpgPowered) {
                       kmOnLpg.removeAttribute("disabled");
                       lpgPrice.removeAttribute("disabled");
                       kmOnPb.removeAttribute("disabled");
                       pbPrice.removeAttribute("disabled");
                   } else if (car.pbPowered) {
                       kmOnPb.removeAttribute("disabled");
                       pbPrice.removeAttribute("disabled");
                   } else if (car.onPowered) {
                       kmOnOn.removeAttribute("disabled");
                       onPrice.removeAttribute("disabled")
                   } else {
                       document.getElementById('welcome').innerHTML = `<h2>choosen car has no type of fuel set</h2>`;
                   }
               }
        function showDate() {
            const now = new Date();
            const currentYear = now.getFullYear().toString();
            const currentMonth = (now.getMonth() + 1).toString();
            const currentDay = now.getDate().toString();
            const formattedDate = `${currentDay.toString().padStart(2, '0')}-${currentMonth.padStart(2, '0')}-${currentYear.padStart(2, '0')}`;
            document.getElementById('addDate').innerHTML = "today: " + formattedDate + "; ";
            document.getElementById('footerDate').innerHTML = "© " + currentYear + " FuelCost, Inc.";
        }

        function processOkResponse(response = {}) {
            if (response.ok) {
                return response.json();
            }
            alert(`HTTP status not 200  (${response.status})`);
            document.forms[0].reset();
            throw new Error(`Status not 200 (${response.status})`);
        }

        function processOkUser(response = {}) {
            if (response.ok) {
                return response;
            }
            alert(`HTTP status not 200  (${response.status})`);
            document.forms[0].reset();
            document.getElementById('loggedUser').innerHTML = `show user Error`;
            throw new Error(`Status not 200 (${response.status})`);
        }
    })();