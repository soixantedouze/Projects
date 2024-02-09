# My Personal Project: Agenda
For my project this term I would like to build a 
digital **Agenda**. This project will
allow me to input important deadlines and highlight 
any important dates coming
up. It should look like a calendar or a planner 
where each day can have 
something written in it like an upcoming test or 
homework due date. 
Then when the date is coming 
up the application should remind me
that there is something important coming up by 
either highlighting it
or having a big reminder popup.
## Proposal Questions:
- The application will allow users to input a "course" 
and an important assignment in the course with a date
then the application will remind them of upcoming important dates
- Any student can use this application, but most likely
I will need to use an application like this to keep track
of upcoming deadlines.
- This project is important to me because it can directly
help me improve my organisation and help me remember to do
things since I am very forgetful and I tend to miss alot of
important dates.

## User Stories:
- As a user, I want to add a deadline to my agenda.
- As a user, I want to be able to see all the deadlines laid out
- As a user, I want to be able to add details to the deadlines (task, course, date)
- As a user, I want to be able to delete something I finished ahead of time.
- As a user, I want to be able to search for a deadline in my agenda
- As a user, I want to be able to edit my tasks/courses/dates
- As a user, I want to be able to save my agenda to file (if I choose to do so)
- As a user, I want to be able to load my agenda from file (if I choose to do so)

## Instructions for Grader:
- You can generate the first required action related to adding Xs to a Y by clicking the finish deadline button to 
finish a deadline and remove it from your agenda after inputting course and task of finished deadline
- You can generate the second required action related to adding Xs to a Y by clicking the edit deadline button to input
the course and task of the deadline you want to edit when prompted to change deadline
- You can locate my visual component by finishing a deadline and seeing a
popup window of a chicken dinner congratulating you
- You can save the state of my application by clicking the save agenda button.
- You can reload the state of my application by clicking the load agenda button.

## Phase 4: Task 2
- When adding a deadline: course: cpsc task: lab month: 3 day: 5
Wed Apr 12 14:37:24 PDT 202
Added new Deadline: cpsc lab 3 5
- When removing a deadline: course: cpsc task: lab month: 3 day: 5
Wed Apr 12 14:38:32 PDT 2023
Removed Deadline: cpsc lab 3 5
- When Editing a deadline: course: cpsc task: lab month: 3 day: 5
to deadline: course: math task: hw month: 4 day: 6
  Wed Apr 12 14:39:25 PDT 2023
  Changed Course cpsc to math
  Wed Apr 12 14:39:25 PDT 2023
  Changed Task lab to hw
  Wed Apr 12 14:39:25 PDT 2023
  Changed Date 3, 5 to 4, 6
## Phase 4: Task 3
- I think that my code can encounter a number of exception errors that
require the user to input specific inputs to avoid. I think I can catch/throw
more exceptions to make the code robust. For example, my deadline inputs for
month and day cannot take in more strings/letters and my course and task
input cannot take in space in the AgendaApp. These could be avoided
with some exceptions. The GUI is very basic and does not appear
too good to the eye so adding things that make it stand out and easier
to look at and use would also be very helpful. Some buttons can be replaced
with a dropbox and maybe a number pad can be implemented for the month and day
entry to avoid encountering letter inputs. The edit function I have edits the 
fields in deadline separately which can also cause problems so maybe putting that
together in one method. Also, I call the same button inputs multiple times in the GUI
so it will reduce coupling if i can have a method that makes the exact same type
of input calls so I can reduce coupling. 