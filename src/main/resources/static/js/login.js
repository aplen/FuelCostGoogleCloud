    (function main() {
//          const URL = 'https://wise-mantra-270807.appspot.com';
        const URL = 'http://localhost:8080';
        var username;

        showDate();
        initWelcomeFormClick();


        function initWelcomeFormClick() {
            const welcomeForm = document.getElementById('welcomeForm');

            document.getElementById('logbtn').addEventListener('click', (event) => {

                fetch(`${URL}/users/login`, {
                        method: 'POST',
                        headers: {
                            'Accept': 'text/html',
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({
                            username: welcomeForm.elements.username.value,
                            password: welcomeForm.elements.password.value
                        })
                    })
                    .then(response => response.text())
                    .then(response => {
                        if (response === "User logged") {
                            window.location = `${URL}/index.html`;
                        } else {
                            document.getElementById('loginResp').innerHTML = `${response}`;
                        }
                    });

            });
        }

        function showDate() {
            const now = new Date();
            const currentYear = now.getFullYear().toString();
            const currentMonth = (now.getMonth() + 1).toString();
            const currentDay = now.getDate().toString();
            const formattedDate = `${currentDay.toString().padStart(2, '0')}-${currentMonth.padStart(2, '0')}-${currentYear.padStart(2, '0')}`;
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
    })();