    (function() {
            const API_URL = 'http://localhost:8080/api';

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

            var confirmTxt = "Are you sure to delete select position? ";

            initWelcomeForm();

            function initWelcomeForm() {
                const CODE_TO_EMOJI = {
                    'pl': '🇵🇱',
                    'en': '🇺🇸',
                    'de': '🇩🇪'
                };
                fetch(`${API_URL}/langs`)
                    .then(processOkResponse)
                    .then(langArr => {
                        document.getElementById('langs').innerHTML = langArr.map(lang => `
              <label class="pure-radio">
                <input type="radio" name="lang" value="${lang.langId}">
                ${CODE_TO_EMOJI[lang.langCode]}
              </label>
          `).join('\n');
                        initWelcomeFormClick();
                    });
            }

            function initWelcomeFormClick() {
                const welcomeForm = document.getElementById('welcomeForm');

                document.getElementById('btn').addEventListener('click', (event) => {
                    event.preventDefault();
                    const formObj = {
                        name: welcomeForm.elements.name.value,
                        lang: welcomeForm.elements.lang.value
                    };
                    fetch(`${API_URL}?${new URLSearchParams(formObj)}`)
                        .then(response => response.text())
                        .then((text) => {
                            document.getElementById('welcome').innerHTML = `
                <h2>${text}</br></br>Enter car data or choose from list:</h2>`;
                            welcomeForm.remove();
                            document.getElementById('carForm').style.display = 'block';
                            document.getElementById('allCarsForm').style.display = 'initial';
                        });
                });
            }

            fuelsList.addEventListener("change", fuelFieldsDisable);
            fuelFieldsDisable();

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

            fetch(`${API_URL}/cars`)
                .then(processOkResponse)
                .then(cars => cars.forEach(createNewCar));

            document.getElementById('addCar').addEventListener('click', (event) => {
                event.preventDefault();
                fetch(`${API_URL}/cars`, {
                        method: 'POST',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({
                            name: carName.value,
                            lpgPowered: !lpgOn100Km.disabled,
                            pbPowered: !pbOn100Km.disabled,
                            onPowered: !onOn100Km.disabled,
                            onOn100Km: onOn100Km.value,
                            lpgOn100Km: lpgOn100Km.value,
                            pbOn100Km: pbOn100Km.value
                        })
                    })
                    .then(processOkResponse)
                    .then(createNewCar)
                    .then(fuelFieldsDisable)
                    .catch(console.warn);
            });

            function createNewCar(car) {

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
                    if (confirm(confirmTxt)) {
                        fetch(`${API_URL}/cars/${car.id}`, {
                                method: 'DELETE'
                            })
                            .then(processOkResponse)
                            .then(label.remove())
                            .catch(console.warn);
                    }
                });


                var useButton = document.createElement('button');
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

            document.getElementById('clearButton').addEventListener('click', (event) => {
                event.preventDefault();
                document.forms[0].reset();
            });

            function sendData(car) {

                document.getElementById('calculateButton').addEventListener('click', (event) => {
                    event.preventDefault();

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
                    <h2>the cost of Your trip is ${text}PLN!</h2>`;
                        })

                    .catch(console.warn);
                });
            }

            getActualPrices();

            function getActualPrices() {
                document.getElementById('showPrices').addEventListener('click', (event) => {
                    event.preventDefault();
                    fetch(`${API_URL}/prices`)
                        .then(processOkResponse)
                        .then(price => {
                            showDate();
                            document.getElementById('addPrices').innerHTML = price;
                        })
                });
            }

            function showDate() {
                const now = new Date();
                const currentYear = now.getFullYear().toString();
                const currentMonth = (now.getMonth() + 1).toString();
                const currentDay = now.getDate().toString();
                const formattedDate = `${currentDay.toString().padStart(2, '0')}-${currentMonth.padStart(2, '0')}-${currentYear.padStart(2, '0')}`;
                document.getElementById('addDate').innerHTML = "Date: " + formattedDate + "; ";
            }

            function processOkResponse(response = {}) {
                if (response.ok) {
                    return response.json();
                }
                alert(`HTTP status not 200  (${response.status})`);
                document.forms[0].reset();
                throw new Error(`Status not 200 (${response.status})`);

            }
        })();