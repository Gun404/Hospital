const errorMessage = document.getElementById('error-message');
const registrationTable = document.getElementById('registration-table');
const submitButton = document.getElementById('submit-button');
Image();
// Example data (replace with actual data fetching from backend)
let patientsData = [
    { name: 'John Doe', password: '123456' },
    { name: 'Jane Smith', password: '456789' },
    { name: 'Michael Johnson', password: '15446456' }
];

// Function to populate table with patient data
function populateTable(data) {
    registrationTable.innerHTML = `
        <tr>
            <th>姓名</th>
            <th>密码</th>
            <th>操作</th>
        </tr>
    `;
    data.forEach((patient, index) => {
        registrationTable.innerHTML += `
            <tr id="row-${index}">
                <td>${patient.name}</td>
                <td>${patient.password}</td>
                <td>
                    <button class="approve-button" data-id="${patient.id}">通过</button>
                    <button class="reject-button" data-id="${patient.id}">拒绝</button>
                </td>
            </tr>
        `;
    });

    // Add event listeners to approve and reject buttons
    const approveButtons = document.querySelectorAll('.approve-button');
    const rejectButtons = document.querySelectorAll('.reject-button');

    approveButtons.forEach(button => {
        button.addEventListener('click', approvePatient);
    });

    rejectButtons.forEach(button => {
        button.addEventListener('click', rejectPatient);
    });
}

// Initial population of table with data
populateTable(patientsData);

// Function to handle approval of a patient
function approvePatient(event) {
    const patientId = event.target.getAttribute('data-id');
    // Send approval action to backend
    axios({
        url: `/admin/patient/new/1/`+patientId,
        method: 'PATCH'
    }).then(result => {
        console.log(result)
        Image();
    }).catch(error => {
        console.error(error);
        alert('操作错误，请联系管理员。');
    });
}

// Function to handle rejection of a patient
function rejectPatient(event) {
    const patientId = event.target.getAttribute('data-id');
    // Send approval action to backend
    axios({
        url: `/admin/patient/new/0/`+patientId,
        method: 'PATCH'
    }).then(result => {
        console.log(result)
        Image();
    }).catch(error => {
        console.error(error);
        alert('操作错误，请联系管理员。');
    });
}

// Function to update table after action
function updateTable() {
    populateTable(patientsData); // Re-populate table with updated data
}

// Function to show error message
function showMessage(message) {
    errorMessage.textContent = message;
    setTimeout(() => {
        errorMessage.textContent = '';
    }, 3000); // Clear message after 3 seconds
}
function goback()
{
    window.location.href = '../admin.html';
}


function Image(){
    axios({
        url: `/admin/patient/new`,
        method: 'GET'
    }).then(result => {
        console.log(result)
        populateTable(result.data.data)
    }).catch(error => {
        console.error(error);
        alert('操作错误，请联系管理员。');
    });
}


