"""

Check where these issue are first from the video around 55 min mark.
1. What if multiple hall calls come in at the same time? What if a hall call comes in while step is running?
->



2. How would you add priority floors or an express elevator?
->
Just add a isEcpress boolean field to the elevator class and those elevators will only service the priority floors.
we will have a priority floors list.



3. How would you add the ability to cancel a floor request?
-> lock acquire -> Just remove request -> lock release.
"""