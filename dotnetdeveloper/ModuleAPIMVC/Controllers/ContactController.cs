using Microsoft.AspNetCore.Mvc;
using ModuleAPIMVC.Context;
using ModuleAPIMVC.Models;

namespace ModuleAPIMVC.Controllers;

public class ContactController(AgendaContext context) : Controller
{
    public IActionResult Index()
    {
        var contacts = context.Contacts.ToList();
        return View(contacts);
    }

    public IActionResult Create()
    {
        return View();
    }

    [HttpPost]
    public IActionResult Create(Contact contact)
    {
        context.Contacts.Add(contact);
        context.SaveChanges();
        return RedirectToAction("Index");
    }

    public IActionResult Details(int id)
    {
        var contact = context.Contacts.Find(id);
        return View(contact);
    }

    public IActionResult Edit(int id)
    {
        var existingContact = context.Contacts.Find(id);

        if (existingContact == null)
            return RedirectToAction(nameof(Index));

        return View(existingContact);
    }

    [HttpPost]
    public IActionResult Edit(Contact contact)
    {
        var existingContact = context.Contacts.Find(contact.Id);

        existingContact.Name = contact.Name;
        existingContact.Phone = contact.Phone;
        existingContact.IsActive = contact.IsActive;

        context.Contacts.Update(existingContact);
        context.SaveChanges();

        return RedirectToAction(nameof(Index));
    }

    public IActionResult Delete(int id)
    {
        var contato = context.Contacts.Find(id);

        if (contato == null)
            return RedirectToAction(nameof(Index));

        return View(contato);
    }

    [HttpPost]
    public IActionResult Delete(Contact contact)
    {
        var existingContact = context.Contacts.Find(contact.Id);

        if (existingContact == null) {
            return RedirectToAction(nameof(Index));
        }

        context.Contacts.Remove(existingContact);
        context.SaveChanges();

        return RedirectToAction(nameof(Index));
    }
}
