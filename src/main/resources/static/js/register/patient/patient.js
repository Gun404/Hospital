const form = document.getElementById('registrationForm');

            form.addEventListener('submit', function(event) {
                event.preventDefault(); // Prevent the form from submitting
                const formData = new FormData(form);
                const data = {};

                formData.forEach((value, key) => {
                    if(key === "age"){
                        if(value===""){
                            data[key]=0
                        }
                        else{
                            data[key]=parseInt(value)
                        }
                        
                    }
                    else if(key === "sex"){
                        const sexid = document.getElementById('sex').value;
                        if(sexid === "male"){
                            data[key] = 1;
                        }else if(sexid === "female"){
                            data[key] = 2;
                        }
                        else{
                            data[key] = 0;  
                        }
                    }
                    else{data[key] = value;}
                    
                });
                // Send data to backend using Axios
                axios({
                    url: '/patient/register',
                    method: 'POST',
                    data: data
                }).then(result => {
                    console.log('注册成功:', result);
                    if(result.data.msg === "success"){
                        alert("注册成功")
                        setTimeout(() => {
                            location.href = "../login/patient_login.html";
                        }, 1000);
                    }
                    
                }).catch(error => {
                    console.error('注册失败:', error);
                    alert('注册失败，请重试！');
                });
            })