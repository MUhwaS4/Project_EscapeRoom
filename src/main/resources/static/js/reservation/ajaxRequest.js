common;
function ajaxRequest(method, url, data = null, onSuccess = null, onError = null) {
    const options = {
        method: method.toUpperCase(),
        headers: {
            'Content-Type': 'application/json',
        }
    };

    // POST일 경우 데이터를 요청에 포함
    if (method.toUpperCase() === 'POST' && data) {
        options.body = JSON.stringify(data);
    }

    fetch(url, options)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            if (onSuccess) {
                onSuccess(data); // 성공 시 동작하는 함수 호출
            }
        })
        .catch(error => {
            if (onError) {
                onError(error); // 실패 시 동작하는 함수 호출
            } else {
                console.error('There was a problem with your fetch operation:', error);
            }
        });
}
