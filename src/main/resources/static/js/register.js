    (function main() {
//        const URL = 'https://wise-mantra-270807.appspot.com';
        const URL = 'http://localhost:8080';
        var username;

        showDate();
        initWelcomeForm();

        function initWelcomeForm() {
            const CODE_TO_EMOJI = {
                'pl': '🇵🇱',
                'en': '🇺🇸',
                'de': '🇩🇪'
            };
            fetch(`${URL}/api/langs`)
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
            const welcomeForm = document.getElementById('registerForm');

            document.getElementById('adduserbtn').addEventListener('click', (event) => {

                fetch(`${URL}/users/register`, {
                        method: 'POST',
                        headers: {
                            'Accept': 'text/html',
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({
                            username: welcomeForm.elements.username.value,
                            password: welcomeForm.elements.password.value,
                            langId: welcomeForm.elements.lang.value
                        })
                    })
                    .then(response => response.text())
                    .then(response => {
                        if (response === "user created, please login") {
                            alert(`${response}`);
                            window.location = `${URL}/index.html`;
                        } else {
                            document.getElementById('registerResp').innerHTML = `${response}`;
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