document.addEventListener('DOMContentLoaded', function() {
    const downloadBtn = document.getElementById('startDownload');
    const uploadBtn = document.getElementById('startUpload');
    const downloadSpeed = document.getElementById('downloadSpeed');
    const uploadSpeed = document.getElementById('uploadSpeed');
    const testProgress = document.getElementById('testProgress');
    const testResults = document.getElementById('testResults');
    const resultDownload = document.getElementById('resultDownload');
    const resultUpload = document.getElementById('resultUpload');
    const resultPing = document.getElementById('resultPing');

    // Simulate ping test
    function testPing() {
        return Math.floor(Math.random() * 100); // Random ping between 0-100ms
    }

    // Animate progress bar
    function animateProgress(duration, callback) {
        let start = null;
        testProgress.style.width = '0%';
        testProgress.style.display = 'block';
        
        function step(timestamp) {
            if (!start) start = timestamp;
            const progress = Math.min((timestamp - start) / duration, 1);
            testProgress.style.width = `${progress * 100}%`;
            
            if (progress < 1) {
                window.requestAnimationFrame(step);
            } else {
                if (callback) callback();
            }
        }
        
        window.requestAnimationFrame(step);
    }

    // Common POST request function
    function runSpeedTest(testType, onComplete) {
        fetch('/start-test', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: 'testType=' + encodeURIComponent(testType)
        })
        .then(response => response.text())
        .then(result => {
            onComplete(result);
        })
        .catch(error => {
            console.error('Error:', error);
            onComplete("Error");
        });
    }

    // Download test
    downloadBtn.addEventListener('click', function() {
        downloadBtn.disabled = true;
        downloadSpeed.textContent = 'Testing...';
        
        animateProgress(3000, function() {
            runSpeedTest('download', function(speed) {
                downloadSpeed.textContent = speed;
                resultDownload.textContent = speed;
                resultPing.textContent = testPing();
                testResults.style.display = 'block';
                downloadBtn.disabled = false;
            });
        });
    });

    // Upload test
    uploadBtn.addEventListener('click', function() {
        uploadBtn.disabled = true;
        uploadSpeed.textContent = 'Testing...';
        
        animateProgress(3000, function() {
            runSpeedTest('upload', function(speed) {
                uploadSpeed.textContent = speed;
                resultUpload.textContent = speed;
                testResults.style.display = 'block';
                uploadBtn.disabled = false;
            });
        });
    });
});
