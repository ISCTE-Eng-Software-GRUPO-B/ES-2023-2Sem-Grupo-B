const form = document.querySelector('form');

form.addEventListener('submit', (event) => {
    event.preventDefault();

    const selectedType = document.querySelector('input[name="type"]:checked').value;

    const formData = new FormData();

    if (selectedType === 'CSV' || selectedType === 'JSON') {
        const fileInput = document.querySelector('input[type="file"]');
        formData.append('file', fileInput.files[0]);
    } else if (selectedType === 'URL') {
        const urlInput = document.querySelector('input[name="url"]');
        formData.append('url', urlInput.value);
    }

    let endpoint;
    if (selectedType === 'CSV' || selectedType === 'JSON') {
        endpoint = 'http://localhost:8256/calendar/consume/file';
        fetch(endpoint, {
            method: 'POST',
            body: formData
        })
            .then(response => {
                if (response.ok) {
                    alert('Dados enviados com sucesso!');
                } else {
                    alert('Ocorreu um erro ao enviar os dados.');
                }
            })
            .catch(error => {
                alert('Ocorreu um erro ao enviar os dados.');
                console.error(error);
            });



    } else if (selectedType === 'URL') {
        endpoint = 'http://localhost:8256/calendar/consume/url';
        fetch(endpoint, {
            method: 'POST',
            body: formData
        })
            .then(response => {
                if (response.ok) {
                    alert('Dados enviados com sucesso!');
                } else {
                    alert('Ocorreu um erro ao enviar os dados.');
                }
            })
            .catch(error => {
                alert('Ocorreu um erro ao enviar os dados.');
                console.error(error);
            });
    }


});
