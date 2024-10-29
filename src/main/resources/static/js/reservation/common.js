const commonjs = {
    ajaxRequest : function (method, url, data = null, onSuccess = null, onError = null) {
        const options = {
            method: method.toUpperCase(),
            headers: {
                'Content-Type': 'application/json',
            }
        };
        
        // GET 요청 시 쿼리 문자열 생성
        if (method.toUpperCase() === 'GET' && data) {
            const queryString = new URLSearchParams(data).toString();
            url += (url.includes('?') ? '&' : '?') + queryString; // URL에 쿼리 문자열 추가
        }
        
        // POST, PUT일 경우 데이터를 요청에 포함
        if ((method.toUpperCase() === 'POST' || method.toUpperCase() === 'PUT') && data) {
            options.body = JSON.stringify(data);
        }
		
		
        fetch(url, options)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok ' + response.statusText);
                }
                return response.text(); // 응답을 텍스트로 받아옴
            })
            .then(text => {
                if (text) { // 응답이 비어 있지 않은 경우에만 JSON 파싱
                    try {
                        const data = JSON.parse(text);
                        if (onSuccess) {
                            onSuccess(data);  // 성공 시 동작하는 함수 호출
                        }
                    } catch (error) {
                        console.error('JSON 파싱 에러:', error, '응답:', text);
                        if (onError) {
                            onError(error);  // 실패 시 동작하는 함수 호출
                        }
                    }
                } else {
                    if (onSuccess) {
                        onSuccess(null); // 빈 응답을 처리할 수 있도록 null 전달
                    }
                }
            })
            .catch(error => {
                if (onError) {
                    onError(error);  // 실패 시 동작하는 함수 호출
                } else {
                    console.error('There was a problem with your fetch operation:', error);
                }
            });
    },

	// 삭제 요청 함수 수정 - DELETE 메서드 사용
	    deleteReservation: function (reservationNo, onSuccess, onError) {
	        const url = `/reservation/delete/${reservationNo}`;
	        const options = {
	            method: 'DELETE',
	            headers: {
	                'Content-Type': 'application/json',
	            }
	        };

	        fetch(url, options)
	            .then(response => {
	                if (!response.ok) {
	                    throw new Error('Failed to delete reservation: ' + response.statusText);
	                }
	                if (onSuccess) onSuccess(); // 성공 시 호출
	            })
	            .catch(error => {
	                if (onError) onError(error);
	                else console.error('There was a problem with deleting the reservation:', error);
	            });
	    }
};

export default commonjs;
