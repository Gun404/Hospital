function goBack() {
    window.location.href = '../../index.html';
}
const form = document.getElementById('loginForm');

            form.addEventListener('submit', function(event) {
                event.preventDefault(); 

                const name = document.getElementById('email').value;
                const password = document.getElementById('password').value;

    
                axios({
                    url: '/admin/login', 
                    method: 'POST',
                    data:{
                        name,
                        password
                    }
                }).then(result => {
                    console.log('登录成功:', result);
                    localStorage.setItem('jwt', result.data.data.jwt)
                    localStorage.setItem('mustChangePassword', result.data.data.mustChangePassword)
                    localStorage.setItem('id', result.data.data.id)
                    if(result.data.msg === "success"){
                        if(result.data.data.mustChangePassword === 1){
                            location.href = '../admin/information/admin_information_update.html'
                        }
                        else{
                            location.href = '../admin/admin.html'
                        }
                    }
                    
                    
                }).catch(error => {
                    console.error('登录失败:', error);
                    alert('登录失败，请重试！');
                });
            });