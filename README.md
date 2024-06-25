Student Information System


This project is a system used to manage a school's student, teacher and course information.
Enrolling students, attending classes, taking grades and tracking their academic performance
is done through this system. 

In addition, teachers should manage their lessons and Tools are provided to evaluate student performance.

API End points

| HTTP Method |     EndPoint      |          Description           |
|:-----------:|:-----------------:|:------------------------------:|
|    POST     | api/auth/register |       Create a new user        |
|    POST     |  api/auth/login   |   User login and take token.   |
|    POST     |  api/auth/logout  | User logout and destroy token. |
|     GET     |  api/users/{id}   |      Fetch a users by id       |
|     GET     |     api/users     |        Fetch all users.        |
|     PUT     |  api/users/{id}   |      Update a users by id      |
|   DELETE    |  api/users/{id}   |      Delete a users by id      |


| HTTP Method |     EndPoint      |        Description         |
|:-----------:|:-----------------:|:--------------------------:|
|    POST     |   api/teachers    |   Create a new teacher.    |
|     GET     |   api/teachers    |    Fetch all teachers.     |
|     PUT     | api/teachers/{id} |   Update a teacher by id   |
|     GET     | api/teachers/{id} |   Fetch a teachers by id   |
|     PUT     |   api/teachers//{teacherId}/lessons/{lessonId}   |  Assign lesson to teacher  |
|     PUT     | api/teachers/{teacherId}/delete-lesson/{lessonId} | Delete a lesson to teacher |
|   DELETE    | api/teachers/{id} |  Delete a teachers by id   |


| HTTP Method |                      EndPoint                      |         Description         |
|:-----------:|:--------------------------------------------------:|:---------------------------:|
|    POST     |                    api/students                    |    Create a new student.    |
|     GET     |                    api/students                    |     Fetch all students.     |
|     PUT     |                 api/students/{id}                  |   Update a student by id    |
|     GET     |                 api/students/{id}                  |    Fetch a student by id    |
|     PUT     |   api/teachers//{studentsId}/lessons/{lessonId}    |  Assign lesson to students  |
|     PUT     | api/teachers/{studentsId}/delete-lesson/{lessonId} | Delete a lesson to students |
|   DELETE    |                 api/students/{id}                  |   Delete a student by id    |


| HTTP Method |     EndPoint     |      Description      |
|:-----------:|:----------------:|:---------------------:|
|    POST     |   api/lessons    | Create a new lesson.  |
|     GET     |   api/lessons    |   Fetch all lesson.   |
|     PUT     | api/lessons/{id} | Update a lesson by id |
|     GET     | api/lessons/{id} | Fetch a lesson by id  |
|   DELETE    | api/lessons/{id} | Delete a lesson by id |


| HTTP Method |    EndPoint    |     Description     |
|:-----------:|:--------------:|:-------------------:|
|    POST     |   api/exams    | Create a new exam.  |
|     GET     |   api/exams    |  Fetch all exams.   |
|     PUT     | api/exams/{id} | Update a exam by id |
|     GET     | api/exams/{id} | Fetch a exam by id  |
|   DELETE    | api/exams/{id} | Delete a exam by id |


| HTTP Method |       EndPoint       |        Description        |
|:-----------:|:--------------------:|:-------------------------:|
|    POST     |   api/attendances    | Create a new attendance.  |
|     GET     |   api/attendances    |  Fetch all attendances.   |
|     PUT     | api/attendances/{id} | Update a attendance by id |
|     GET     | api/attendances/{id} | Fetch a attendance by id  |
|   DELETE    | api/attendances/{id} | Delete a attendance by id |